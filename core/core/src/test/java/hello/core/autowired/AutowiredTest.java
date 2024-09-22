package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        //스프링에서 관리되는 것 없는 아무거나 넣음
        @Autowired(required = false) //의존관계 없으면 메서드 호출자체가 안됨
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){//호출은 되지만 null로 들어옴
            System.out.println("noBean2 = " + noBean2);

        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){//값 없으면 Optional.empty반환
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
