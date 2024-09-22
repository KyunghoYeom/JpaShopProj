package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member",//시작 위치 지정가능->없으면 모든 자바 코드 다 뒤짐 -> 오래걸리는 문제 방지
        //basePackageClasses = AutoAppConfig.class, // 해당 클래스의 패키지 부터 찾는다 의미 -> 여기에서는 hello.core부터
        //디폴트값은(지정하지 않으면) -> hello.core부터 시작함(@ComponentScan붙은 클래스 패키지가 시작 위치)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //적용하지 않을 대상 Filter로 지정 -> Configuration 어노테이션이 붙은 class 제외 -> AppConfig 제외위해

)
//@Component annotation 붙은 클래스 다 자동으로 spring bean 등록해줌
public class AutoAppConfig {

}
