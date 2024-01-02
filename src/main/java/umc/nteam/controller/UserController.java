package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.nteam.auth.AuthUser;
import umc.nteam.auth.JwtProvider;
import umc.nteam.domain.User;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity loginUser() {
        String token = jwtProvider.createToken(0L);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/auth/register")
    public ResponseEntity registryUser() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/users/friend")
    public ResponseEntity addFriend() {
        return ResponseEntity.ok(null);
    }
    @GetMapping("/users/friends")
    public ResponseEntity showFriendList() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/users/friend/{friendId}")
    public ResponseEntity showFriendList(@PathVariable Long friendId) {
        return ResponseEntity.ok(null);
    }
}
