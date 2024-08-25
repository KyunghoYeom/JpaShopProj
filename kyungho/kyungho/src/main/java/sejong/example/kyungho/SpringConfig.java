package sejong.example.kyungho;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sejong.example.kyungho.repository.JdbcTemplateMemberRepository;
import sejong.example.kyungho.repository.JpaMemberRepository;
import sejong.example.kyungho.repository.MemberRepository;
import sejong.example.kyungho.repository.MemoryMemberRepository;
import sejong.example.kyungho.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
