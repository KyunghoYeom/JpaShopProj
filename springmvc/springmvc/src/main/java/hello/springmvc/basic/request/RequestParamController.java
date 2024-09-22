package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        System.out.println("age = " + age);
        response.getWriter().write("ok");


    }

    @ResponseBody//반환하는 문자를 그대로 http응답 message넣어서 반환하는 효과
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }


    @ResponseBody//반환하는 문자를 그대로 http응답 message넣어서 반환하는 효과
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            //parameter이름과 동일한 이름일 경우 생략 가능
            @RequestParam String username,
            @RequestParam int age
    ){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody//반환하는 문자를 그대로 http응답 message넣어서 반환하는 효과
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            //parameter이름과 동일한 이름일 경우 생략 가능
            //@RequestParam도 생략 가능
            String username,
            int age
    ){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            //Integer라 쓴 이유는 int 형은 null을 받지 못하기에 이렇게 적음
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(

            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
            //defaultValue들어가게 되면 required의미 없어짐-> 값 있으면 해당 값 적고 없으면 기본 값으로 대체되므로
            //defaultValue는 빈 문자의 경우에도 디폴트 값으로 대체된다 ex-> username=
    ){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
}
