package umc.nteam.dto;

import lombok.Getter;

public class FundDto {

    @Getter
    public static class FundRequestDto {
        Long memberId;
        Integer price;
    }

}