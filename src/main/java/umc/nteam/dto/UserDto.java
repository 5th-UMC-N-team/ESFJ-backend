package umc.nteam.dto;

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
        String name;
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
