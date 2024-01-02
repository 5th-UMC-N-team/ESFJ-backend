package umc.nteam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.nteam.domain.enums.FundWishStatus;

import java.util.List;

public class WishDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WishGetMyListResponseDto {
        String profileUrl;
        List<WishListDto> wishListDto;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WishGetFriendListResponseDto {
        Long userId;
        String profileUrl;
        String name;
        List<WishListDto> wishListDto;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WishListDto {
        Long wishId;
        String name;
        String imageUrl;
        String reason;
        int price;
        FundWishStatus fundWishStatus;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WishAddRequestDto {
        String name;
        int price;
        String reason;
        String link;
        FundWishStatus fundWishStatus;  // 모금 선택 여부
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WishGetDetailResponseDto {
        Long wishId;
        String name;
        int price;
        String reason;
        String link;
        String imageUrl;
        int fundPrice;  // 현재까지 모인 모긍액
        float percentage;
    }
}
