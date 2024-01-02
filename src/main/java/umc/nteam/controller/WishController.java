package umc.nteam.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.nteam.auth.AuthUser;
import umc.nteam.domain.User;
import umc.nteam.domain.enums.FundWishStatus;
import umc.nteam.dto.SuccessDto;
import umc.nteam.service.WishService;
import umc.nteam.dto.WishDto;
import umc.nteam.dto.WishDto.WishAddRequestDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishes")
public class WishController {

    private final WishService wishService;

    // 나의 위시리스트 조회 api
    @GetMapping("")
    public ResponseEntity<WishDto.WishGetMyListResponseDto> getMyWishes(@RequestParam("price-range") int priceRange, @AuthUser User user) {
        WishDto.WishGetMyListResponseDto wishGetMyListResponseDto = wishService.getMyList(user, priceRange);
        return ResponseEntity.ok(wishGetMyListResponseDto);
    }

    // 친구의 위시리스트 조회 api
    @GetMapping("/friends/{friendId}")
    public ResponseEntity<WishDto.WishGetFriendListResponseDto> getFriendWishes(@PathVariable Long friendId,
        @RequestParam("price-range") int priceRange) {
        WishDto.WishGetFriendListResponseDto wishGetFriendListResponseDto = wishService.getFriendList(friendId, priceRange);

        return ResponseEntity.ok(wishGetFriendListResponseDto);
    }

    // 위시 추가 api
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Parameters({
        @Parameter(name = "name", description = "상품의 이름"),
        @Parameter(name = "price", description = "상품의 가격"),
        @Parameter(name = "reason", description = "상품을 원하는 이유"),
        @Parameter(name = "link", description = "상품 구매처 링크"),
        @Parameter(name = "fundWishStatus", description = "!!TRUE 혹은 FALSE만 입력해주세요!! 모금 받을지 여부")
    })
    public ResponseEntity<SuccessDto> addWish(
        @AuthUser User user,
        @RequestPart(value = "file", required = false) MultipartFile file,
        @RequestParam(name = "name") String name,
        @RequestParam(name = "price") int price,
        @RequestParam(name = "reason") String reason,
        @RequestParam(name = "link") String link,
        @RequestParam(name = "fundWithStatus") String fundWishStatus) throws IOException {

        WishAddRequestDto requestDto = WishAddRequestDto.builder()
            .name(name)
            .price(price)
            .reason(reason)
            .link(link)
            .fundWishStatus(FundWishStatus.valueOf(fundWishStatus))
            .build();
        wishService.createWish(user, file, requestDto);
        return ResponseEntity.ok(SuccessDto.builder().isSuccess(true).build());
    }


      
    // 위시 상세 조회 api
    @GetMapping("/{wishId}")
    public ResponseEntity<WishDto.WishGetDetailResponseDto> getWish(@PathVariable Long wishId){
        WishDto.WishGetDetailResponseDto wishGetDetailResponseDto = wishService.getDetail(wishId);

        return ResponseEntity.ok(wishGetDetailResponseDto);
    }

}
