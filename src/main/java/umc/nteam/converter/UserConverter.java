package umc.nteam.converter;

import umc.nteam.domain.User;
import umc.nteam.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    public static UserDto.UserCardListDto toUserCardListDto(List<User> users) {
        List<UserDto.UserCardDto> userCardDtos = users.stream()
                .map(UserConverter::toUserCardDto)
                .collect(Collectors.toList());

        return new UserDto.UserCardListDto(userCardDtos);
    }

    public static UserDto.UserCardDto toUserCardDto(User user) {
        return UserDto.UserCardDto.builder()
                .id(user.getId())
                .profileUrl(user.getProfileUrl())
                .name(user.getName())
                .build();
    }

}
