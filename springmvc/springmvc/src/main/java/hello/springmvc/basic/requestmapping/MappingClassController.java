package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * 회원 목록 조회 : GET  '/users'
     * 회원 등록 : POST  '/users'
     * 회원 조회 : GET  '/users/{userId}'
     * 회원 수정: PATCH  '/users/{userId}'
     * 회원 삭제 : DELETE  '/users/{userId}'
     * @return
     */
    @GetMapping
    public String user(){
        return "get user";
    }
    @PostMapping
    public String addUser(){
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String data){
        return "get userId=" + data;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String data){
        return "update userId=" + data;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String data){
        return "delete userId=" + data;
    }




}
