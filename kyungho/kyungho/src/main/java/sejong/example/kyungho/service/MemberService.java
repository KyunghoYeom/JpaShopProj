package sejong.example.kyungho.service;


import jakarta.transaction.Transactional;
import sejong.example.kyungho.domain.Member;
import sejong.example.kyungho.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member>findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}

