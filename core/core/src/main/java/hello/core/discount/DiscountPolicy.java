package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /*
    return : 할인대상 금액(결과로 얼마 할인 됐는지를 리턴)
     */
    int discount(Member member, int price);
}
