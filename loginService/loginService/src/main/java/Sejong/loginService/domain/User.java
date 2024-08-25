package Sejong.loginService.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity//엔티티 등록
@Data//getter setter 자동 넣어줌
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)//pk 자동증가 방식
    private Long id;

    @NotNull
    @Email
    private String email;
    private String password;
    private String nickname;



}

