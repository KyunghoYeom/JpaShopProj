package jpabook2.jpashop2.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jpabook2.jpashop2.Domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    //item의 경우는 toOne관계이므로 여기에 batchsize를 적지말고 아이템 클래스에 직접적기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    protected OrderItem() {

    }

    //생성 메서드//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;

    }


    //비즈니스 로직
    public void cancel() {
        getItem().addStock(count);
    }

    //조회 로직

    /**
     * 주문 상품 전체 가격 조회
     * @return
     */
    public int getTotalPrice() {

        return getOrderPrice() * getCount();
    }
}
