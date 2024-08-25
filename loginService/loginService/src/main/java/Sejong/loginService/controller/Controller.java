package Sejong.loginService.controller;

import Sejong.loginService.Service.UserService;
import Sejong.loginService.dto.LoginDTO;
import Sejong.loginService.dto.UserDTO;
import Sejong.loginService.dto.UserSignUpDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class Controller {

    private UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserDTO> joinUser(@RequestBody UserSignUpDTO userSignUpDTO) {
        return new ResponseEntity<>(userService.join(userSignUpDTO), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(userService.login(loginDTO), HttpStatus.OK);
    }
}