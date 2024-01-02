package umc.nteam.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishes")
public class WishController {

    // 나의 위시리스트 조회 api
    @GetMapping("")
    public ResponseEntity getMyWishes(){
        return ResponseEntity.ok(null);
    }

    // 친구의 위시리스트 조회 api
    @GetMapping("/{friendId}")
    public ResponseEntity getFriendWishes(@PathVariable Long friendId){
        return ResponseEntity.ok(null);
    }

    // 위시 추가 api
    @PostMapping("")
    public ResponseEntity addWish(){
        return ResponseEntity.ok(null);
    }

    // 위시 상세 조회 api
    @GetMapping("/{wishId}")
    public ResponseEntity getWish(@PathVariable Long wishId){
        return ResponseEntity.ok(null);
    }

    // 위시 삭제 api
    @DeleteMapping("/{wishId}")
    public ResponseEntity deleteWish(@PathVariable Long wishId){
        return ResponseEntity.ok(null);
    }
}
