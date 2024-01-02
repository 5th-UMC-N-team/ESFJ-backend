package umc.nteam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    public static class UserLoginRequestDto {
        String name;
        String password;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginSuccessDto {
        String token;
    }

    /////////////////

    @Getter
    public static class UserRegisterRequestDto {
        @Schema(description = "로그인할 이름. DB 상에서 고유하다.")
        String name;
        @Schema(description = "비밀번호")
        String password;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterResponseDto {
        String name;
    }

    ///////////////

    @Getter
    @AllArgsConstructor
    public static class UserCardListDto {
        List<UserCardDto> userCardDtoList;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class UserCardDto {
        Long id;
        String profileUrl;
        String name;
    }

}
