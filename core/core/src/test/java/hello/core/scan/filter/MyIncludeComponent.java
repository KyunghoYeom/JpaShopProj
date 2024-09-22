package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//얘가 붙은 것은 ComponentScan에 추가한다 의미
public @interface MyIncludeComponent {
    
}
