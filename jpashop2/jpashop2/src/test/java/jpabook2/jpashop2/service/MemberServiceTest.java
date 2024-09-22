package jpabook2.jpashop2.service;

import jpabook2.jpashop2.Domain.Member;
import jpabook2.jpashop2.repository.MemberRepositoryOld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepositoryOld memberRepository;

    @Test
    public void 회원가입(){
        Member member = new Member();
        member.setName("kim");
        Long findId = memberService.join(member);

        assertThat(memberRepository.findOne(findId)).isEqualTo(member);
        //10:57

    }
    @Test
    public void 중복_회원_예외(){
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        memberService.join(member1);
        assertThatThrownBy(()->memberService.join(member2))
                .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("이미 존재하는 회원입니다."); // 메시지 검증 추가
    }


}