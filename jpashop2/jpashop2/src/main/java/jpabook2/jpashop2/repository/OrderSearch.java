package jpabook2.jpashop2.repository;

import jpabook2.jpashop2.Domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
