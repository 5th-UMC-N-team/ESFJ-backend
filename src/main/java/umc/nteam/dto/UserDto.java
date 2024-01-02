package umc.nteam.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    public static class UserLoginRequestDto {
        String id;
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
        String id;
        String password;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterResponseDto {
        String id;
        String password;
    }

    ///////////////

    @Getter
    public static class UserCardListDto {
        List<UserCardDto> userCardDtoList;
    }

    @Getter
    public static class UserCardDto {
        Long id;
        String profileUrl;
        String name;
    }


    /////////

    @Getter
    public static class FriendAddRequestDto {
        String id;
    }

}
