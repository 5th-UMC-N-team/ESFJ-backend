package umc.nteam.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.nteam.domain.User;
import umc.nteam.domain.Wish;
import umc.nteam.repository.WishRepository;
import umc.nteam.web.dto.WishDto;

import java.util.ArrayList;
import java.util.List;

public interface WishService {

    // 내 위시리스트 조회 api
    WishDto.WishGetMyListResponseDto getMyList(User user, int priceRange);

    // 선택한 친구의 위시리스트 조회 api
    WishDto.WishGetFriendListResponseDto getFriendList(Long friendId, int priceRange);
}
