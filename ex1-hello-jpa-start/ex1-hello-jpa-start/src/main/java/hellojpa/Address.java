package hellojpa;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.Objects;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
    //세터를 없애고 생성자로만 값을 바꿀 수 있게 해놓으면은 추후에 값 변경하려 접근해도 불변할 수 있다는 특징 있다

    public String getCity() {
        return city;
    }

   /* public void setCity(String city) {
        this.city = city;
    }
*/
    public String getStreet() {
        return street;
    }

   /* public void setStreet(String street) {
        this.street = street;
    }*/

    public String getZipcode() {
        return zipcode;
    }

   /* public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
