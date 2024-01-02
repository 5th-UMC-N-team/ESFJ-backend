package umc.nteam.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.nteam.auth.AuthUser;
import umc.nteam.domain.User;
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
    public ResponseEntity<WishDto.WishGetMyListResponseDto> getMyWishes(@RequestParam("price-range") int priceRange, @AuthUser User user){
        WishDto.WishGetMyListResponseDto wishGetMyListResponseDto = wishService.getMyList(user, priceRange);
        return ResponseEntity.ok(wishGetMyListResponseDto);
    }

    // 친구의 위시리스트 조회 api
    @GetMapping("/friends/{friendId}")
    public ResponseEntity<WishDto.WishGetFriendListResponseDto> getFriendWishes(@PathVariable Long friendId, @RequestParam("price-range") int priceRange){
        WishDto.WishGetFriendListResponseDto wishGetFriendListResponseDto = wishService.getFriendList(friendId, priceRange);

        return ResponseEntity.ok(wishGetFriendListResponseDto);
    }

    // 위시 추가 api
    @PostMapping("")
    public ResponseEntity<SuccessDto> addWish(@RequestBody WishDto.WishAddRequestDto requestDto){
        return ResponseEntity.ok(null);
    }

    // 위시 상세 조회 api
    @GetMapping("/{wishId}")
    public ResponseEntity<WishDto.WishGetDetailResponseDto> getWish(@PathVariable Long wishId){
        WishDto.WishGetDetailResponseDto wishGetDetailResponseDto = wishService.getDetail(wishId);

        return ResponseEntity.ok(wishGetDetailResponseDto);
    }

}
