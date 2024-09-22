package hello.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
//@Data에서 @EqualsAndHashCode 어노테이션 만들어주므로 test코드에서 객체가 서로 다르더라도 값이 같으면 같다고 나오게 된다
public class Member {
    private String memberId;
    private int money;
    public Member(){

    }





}
