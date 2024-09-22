package hello.hellospring.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false)String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @ResponseBody
    @GetMapping("hello-string")
    public String helloString(@RequestParam String name){
        return "hello "+ name;
    }

    @ResponseBody
    @GetMapping("hello-api")
    public Hello helloApi(@RequestParam String name){
        return new Hello(name, name + "1");


    }


    @Data
    @AllArgsConstructor
    static class Hello{
        private String name;
        private String nickname;
    }


}
