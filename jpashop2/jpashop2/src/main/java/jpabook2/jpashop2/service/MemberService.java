package jpabook2.jpashop2.service;

import jpabook2.jpashop2.Domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import jpabook2.jpashop2.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor//final 필드만 가지고 생성자 만듦
public class MemberService {
    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        //save에서 em.persist를 하는 순간에 pk(id)값도 지정되므로(영속성 컨텍스트 저장될 때) getId 사용가능해짐
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional
    public void update(Long id, String name) {
        Member findMember = memberRepository.findById(id).get();
        findMember.setName(name);

    }

    //회원 전체 조회

    public List<Member>findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){

        return memberRepository.findById(memberId).get();
    }


}
