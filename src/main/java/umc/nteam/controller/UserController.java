package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/auth/register")
    public ResponseEntity registryUser() {
        return ResponseEntity.ok(null);
    }
    @PostMapping("/auth/login")
    public ResponseEntity loginUser() {
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
