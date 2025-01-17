package jpabook2.jpashop2.service.query;

import jpabook2.jpashop2.Domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto
    {
        private String itemName;
        private int orderPrice;
        private int count;
        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getItem().getPrice();
            count = orderItem.getCount();
        }
    }