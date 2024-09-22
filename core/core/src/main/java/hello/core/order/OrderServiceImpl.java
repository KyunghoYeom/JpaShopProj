package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor//final 붙은 것 가지고 생성자를 자동으로 만들어주는 기능
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //final은 무조건 값이 있어야 된다를 의미
    //처음에 아에 값을 넣어주든지 아니면 생성자 주입을 하든지 둘 중 하나를 해야된다

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        //이 생성자 역할을 lombok의 required~~가 대신 해줌

        //민약 Autowired 해야되는데 타입이 동일한 bean이 두개 이상 등록되어있다면 변수(파라미터)명을 보고 같은 변수명을 가진
        //빈을 반환해줌 -> 이경우에는 중복된 빈이 있더라도 오류 없이 정상적으로 실행됨
        //DiscountPolicy rateDiscountPolicy , this.discountPolicy = rateDiscountPolicy;

        //다른 방법으로는 qualifier 지정해주기
        
        //스프링은 항상 자동보다는 수동이 우선순위 높기에 qualifier가 primary보다 우선순위 높다

    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
    
    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
