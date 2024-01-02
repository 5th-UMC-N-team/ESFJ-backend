package umc.nteam.web.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.nteam.dto.SuccessDto;
import umc.nteam.service.WishService;
import umc.nteam.web.dto.WishDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishes")
public class WishController {

    private final WishService wishService;

    // 나의 위시리스트 조회 api
    @GetMapping("")
    public ResponseEntity<WishDto.WishGetMyListResponseDto> getMyWishes(@RequestParam("price-range") int priceRange) {
        return ResponseEntity.ok(null);
    }

    // 친구의 위시리스트 조회 api
    @GetMapping("/{friendId}")
    public ResponseEntity<WishDto.WishGetFriendListResponseDto> getFriendWishes(@PathVariable Long friendId,
        @RequestParam("price-range") int priceRange) {

        return ResponseEntity.ok(null);
    }

    // 위시 추가 api
    @PostMapping("")
    public ResponseEntity<SuccessDto> addWish(@RequestBody WishDto.WishAddRequestDto requestDto) throws IOException {
        wishService.createWish(requestDto);
        return ResponseEntity.ok(SuccessDto.builder().isSuccess(true).build());
    }

    // 위시 상세 조회 api
    @GetMapping("/{wishId}")
    public ResponseEntity<WishDto.WishGetDetailResponseDto> getWish(@PathVariable Long wishId) {
        return ResponseEntity.ok(null);
    }

    // 위시 삭제 api
    @DeleteMapping("/{wishId}")
    public ResponseEntity<SuccessDto> deleteWish(@PathVariable Long wishId) {
        return ResponseEntity.ok(null);
    }
}
