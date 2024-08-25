package Sejong.loginService.dto;

import lombok.Data;

@Data
public class UserSignUpDTO {
    private String email;
    private String password;
    private String nickname;
}
