package jpabook2.jpashop2.Controller;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {
    private Long id; //상품을 수정할 수 있어야하므로 id값을 받음

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
