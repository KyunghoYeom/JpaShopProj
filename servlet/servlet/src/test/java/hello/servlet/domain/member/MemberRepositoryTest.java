package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void save(){
        //given
        Member member = new Member("hello", 20);


        //when
        Member savedMember = memberRepository.save(member);

        //then

        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> list = memberRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(member1, member2);

    }



}
