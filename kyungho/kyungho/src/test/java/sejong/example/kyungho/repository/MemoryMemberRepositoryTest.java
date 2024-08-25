package sejong.example.kyungho.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sejong.example.kyungho.domain.Member;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("java");
        memberRepository.save(member);
        Member result = memberRepository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);


    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);

    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> list = memberRepository.findAll();
        assertThat(list.size()).isEqualTo(2);
    }

}
