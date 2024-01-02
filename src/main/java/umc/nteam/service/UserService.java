package umc.nteam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import umc.nteam.auth.JwtProvider;
import umc.nteam.domain.User;
import umc.nteam.dto.UserDto;
import umc.nteam.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(UserDto.UserLoginRequestDto userLoginRequestDto) {
        String name = userLoginRequestDto.getName();
        String password = userLoginRequestDto.getPassword();

        Optional<User> user = userRepository.findByName(name);

        if (
                user.isEmpty() ||
                        !passwordEncoder.matches(password, user.get().getPassword())
        ) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "존재하지 않는 아이디 또는 패스워드입니다."
            );
        }

        return jwtProvider.createToken(user.get().getId());
    }

    public void register(UserDto.UserRegisterRequestDto userRegisterRequestDto) {
        String name = userRegisterRequestDto.getName();
        String password = userRegisterRequestDto.getPassword();

        Optional<User> existUser = userRepository.findByName(name);

        if (existUser.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "이미 존재하는 아이디입니다."
            );
        }

        String encryptedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .name(name)
                .password(encryptedPassword)
                .birthday(LocalDate.of(2024,1,1))
                .friendList(new ArrayList<>())
                .profileUrl("")
                .build();

        userRepository.save(user);
    }

    public void addFriend(UserDto.FriendAddRequestDto friendAddRequestDto) {

    }


    public List<User> showFriendList(User user) {
        List<User> friendList = user.getFriendList();
        return friendList;
    }
}
