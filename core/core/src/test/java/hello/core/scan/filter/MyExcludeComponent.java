package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//얘가 붙은 것은 ComponentScan에서 제외한다 의미
public @interface MyExcludeComponent {
    
}
