package umc.nteam.converter;

import umc.nteam.domain.Wish;
import umc.nteam.web.dto.WishDto.WishAddRequestDto;

public class WishConverter {

    public static Wish toWish(WishAddRequestDto requestDto, String imageUrl) {
        return Wish.builder()
            .name(requestDto.getName())
            .price(requestDto.getPrice())
            .reason(requestDto.getReason())
            .link(requestDto.getLink())
            .imageUrl(imageUrl)
            .fundWishStatus(requestDto.getFundWishStatus())
            .build();
    }
}
