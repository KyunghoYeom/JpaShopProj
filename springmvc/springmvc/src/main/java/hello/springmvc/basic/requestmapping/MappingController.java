package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello-basic")
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @return
     * /mapping/userA 이런식으로 url 입력 받아지는 경우
     */
    @GetMapping("/mapping/{userid}")
    public String mappingPath(@PathVariable("userid")String data){
        log.info("mappingPath userid={}", data);
        return "ok";
    }

    /**
     * consume은 요청 헤더의 컨텐트 타입, produce는 요청 헤더의 accept 기반 매칭
     */


}
