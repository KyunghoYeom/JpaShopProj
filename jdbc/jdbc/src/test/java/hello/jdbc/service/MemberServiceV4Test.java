package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import hello.jdbc.repository.MemberRepositoryV3;
import hello.jdbc.repository.MemberRepositoryV4_1;
import hello.jdbc.repository.MemberRepositoryV4_2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * 예외 누수 문제 해결
 *  SQLException 제거
 *  MemberRepository 인터페이스 제거
 * */
@Slf4j
@SpringBootTest
class MemberServiceV4Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberServiceV4 memberService;
    @TestConfiguration
    static class TestConfig{
        private final DataSource dataSource;

        public TestConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Bean
        MemberRepository memberRepository(DataSource dataSource){
            return new MemberRepositoryV4_2(dataSource);
        }
        @Bean
        MemberServiceV4 memberServiceV4(MemberRepository memberRepository)
        {
            return new MemberServiceV4(memberRepository);
        }



    }


    @AfterEach
    void afterEach(){
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer(){
        //given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 20000);
         memberRepository.save(memberA);
        memberRepository.save(memberB);

        //when
        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 1000);

        //then
        assertThat(memberRepository.findById(memberA.getMemberId()).getMoney()).isEqualTo(9000);
        assertThat(memberRepository.findById(memberB.getMemberId()).getMoney()).isEqualTo(21000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void accountTransferEx(){
        //given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberEx = new Member(MEMBER_EX, 20000);
        memberRepository.save(memberA);
        memberRepository.save(memberEx);

        //when
        assertThatThrownBy(()->memberService.accountTransfer(memberA.getMemberId(), memberEx.getMemberId(), 1000))
                .isInstanceOf(IllegalStateException.class);


        //then
        assertThat(memberRepository.findById(memberA.getMemberId()).getMoney()).isEqualTo(10000);
        assertThat(memberRepository.findById(memberEx.getMemberId()).getMoney()).isEqualTo(20000);
    }
}