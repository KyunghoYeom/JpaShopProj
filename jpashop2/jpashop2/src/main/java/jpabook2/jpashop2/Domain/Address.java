package jpabook2.jpashop2.Domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
//immutable하게 만들어져야하는 특징이 있다.
//따라서 getter는 열어놓되 setter대신에 생성자 주입을 받고 이후 변경되지 못하도록 막아야함
//이때 기본생성자가 필요하기에(jpa 스펙상) protected를 통해 잘못된 접근은 막으면서 만들어주면됨
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected  Address()
    {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
