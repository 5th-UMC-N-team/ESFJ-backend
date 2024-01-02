package umc.nteam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class FundDto {

    @Getter
    public static class FundRequestDto {
        @Schema(description = "모금할 금액")
        Integer price;
    }

}
