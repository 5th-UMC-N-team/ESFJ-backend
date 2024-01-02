package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.nteam.dto.SuccessDto;
import umc.nteam.dto.UserDto;
import umc.nteam.auth.JwtProvider;
import umc.nteam.dto.UserDto.LoginSuccessDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/register")
    public ResponseEntity<UserDto.LoginSuccessDto> registryUser(@RequestBody UserDto.UserRegisterRequestDto userRegisterRequestDto) {
        return ResponseEntity.ok(null);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<UserDto.LoginSuccessDto> loginUser(@RequestBody UserDto.UserLoginRequestDto userLoginRequestDto) {
        String token = jwtProvider.createToken(0L);
        LoginSuccessDto loginSuccessDto = LoginSuccessDto.builder().token(token).build();
        return ResponseEntity.ok(loginSuccessDto);
    }


    @PostMapping("/users/friend")
    public ResponseEntity<SuccessDto> addFriend(@RequestBody UserDto.FriendAddRequestDto friendAddRequestDto) {
        return ResponseEntity.ok(null);
    }
    @GetMapping("/users/friends")
    public ResponseEntity<UserDto.UserCardListDto> showFriendList() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/users/{friendId}")
    public ResponseEntity<SuccessDto> deleteFriend(@PathVariable Long friendId) {
        return ResponseEntity.ok(null);
    }
}
