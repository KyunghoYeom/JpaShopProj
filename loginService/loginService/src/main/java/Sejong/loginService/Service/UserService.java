package Sejong.loginService.Service;

import Sejong.loginService.domain.User;
import Sejong.loginService.dto.LoginDTO;
import Sejong.loginService.dto.UserDTO;
import Sejong.loginService.dto.UserSignUpDTO;
import Sejong.loginService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor//생성자 자동 생성
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    public UserDTO join(UserSignUpDTO userSignUpDTO){
        validEmailAvailable(userSignUpDTO);
        User user = new User();
        user.setEmail(userSignUpDTO.getEmail());
        user.setNickname(userSignUpDTO.getNickname());
        user.setPassword(encoder.encode(userSignUpDTO.getPassword()));
        userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setNickname(user.getNickname());

        return userDTO;
    }

    private void validEmailAvailable(UserSignUpDTO userSignUpDTO) {
        Optional<User> result = userRepository.findByEmail(userSignUpDTO.getEmail());
        result.ifPresent(m->{
            throw new RuntimeException("중복된 이메일입니다. 다른 이메일을 사용하시오");

        });
    }

    public UserDTO login(LoginDTO loginDTO){
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new RuntimeException("로그인 실패!"));

        if(encoder.matches(loginDTO.getPassword(), user.getPassword())== false){
            throw new RuntimeException("로그인 실패!");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setNickname(user.getNickname());

        return userDTO;

    }



}
