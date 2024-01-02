package umc.nteam.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import umc.nteam.domain.User;
import umc.nteam.domain.Wish;
import umc.nteam.web.dto.WishDto;

import umc.nteam.web.dto.WishDto.WishAddRequestDto;

public interface WishService {

    // 내 위시리스트 조회 api
    WishDto.WishGetMyListResponseDto getMyList(User user, int priceRange);

    // 선택한 친구의 위시리스트 조회 api
    WishDto.WishGetFriendListResponseDto getFriendList(Long friendId, int priceRange);

    Wish createWish(User user, MultipartFile file, WishAddRequestDto requestDto) throws IOException;
    // 선택한 위시의 모금현황 조회 api
    WishDto.WishGetDetailResponseDto getDetail(Long wishId);
}
