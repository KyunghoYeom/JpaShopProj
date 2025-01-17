package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
//@Primary//우선권 가지게 만듦
@MainDiscountPolicy
public class RateDiscountPolicy implements  DiscountPolicy{
    private int discountRate = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){

            return price * discountRate / 100;
        }
        return 0;
    }
}
