package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.nteam.auth.AuthUser;
import umc.nteam.converter.UserConverter;
import umc.nteam.domain.User;
import umc.nteam.dto.SuccessDto;
import umc.nteam.dto.UserDto;
import umc.nteam.dto.UserDto.LoginSuccessDto;
import umc.nteam.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserDto.LoginSuccessDto> registryUser(@RequestBody UserDto.UserRegisterRequestDto userRegisterRequestDto) {
        userService.register(userRegisterRequestDto);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<UserDto.LoginSuccessDto> loginUser(@RequestBody UserDto.UserLoginRequestDto userLoginRequestDto) {
        String token = userService.login(userLoginRequestDto);
        LoginSuccessDto loginSuccessDto = LoginSuccessDto.builder().token(token).build();
        return ResponseEntity.ok(loginSuccessDto);
    }

    @PostMapping("/users/friend")
    public ResponseEntity<SuccessDto> addFriend(@RequestBody UserDto.FriendAddRequestDto friendAddRequestDto) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/users/friends")
    public ResponseEntity<UserDto.UserCardListDto> showFriendList(@AuthUser User user) {
        List<User> friendList = userService.showFriendList(user);
        UserDto.UserCardListDto userCardListDto = UserConverter.toUserCardListDto(friendList);

        return ResponseEntity.ok(userCardListDto);
    }

    @DeleteMapping("/users/{friendId}")
    public ResponseEntity<SuccessDto> deleteFriend(@PathVariable Long friendId) {
        return ResponseEntity.ok(null);
    }
}
