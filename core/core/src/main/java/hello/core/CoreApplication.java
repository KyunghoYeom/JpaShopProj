package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//이 어노테이션 안에 이미 ComponentScan 있으므로 그동안 application 실행하면
//자동으로 spring bean에 등록되어왔던 것
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
