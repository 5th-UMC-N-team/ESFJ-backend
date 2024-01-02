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

@Service
@Transactional
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    // 내 위시리스트 가져오기
    public WishDto.WishGetMyListResponseDto getMyList(User user){
        // 로그인한 유저의 전체 위시 리스트 가져오기
        List<Wish> userWishList = wishRepository.findAllByUserId(user.getId());

        // Dto 리스트에 담기
        List<WishDto.WishListDto> wishListDtoList = new ArrayList<>();
        for(Wish wish: userWishList){
            WishDto.WishListDto wishListDto = WishDto.WishListDto.builder()
                    .wishId(wish.getId())
                    .name(wish.getName())
                    .imageUrl(wish.getImageUrl())
                    .reason(wish.getReason())
                    .price(wish.getPrice())
                    .fundWishStatus(wish.getFundWishStatus())
                    .build();

            wishListDtoList.add(wishListDto);
        }

        // 최종 Response Dto 생성
        WishDto.WishGetMyListResponseDto wishGetMyListResponseDto = WishDto.WishGetMyListResponseDto.builder()
                .profileUrl(user.getProfileUrl())
                .wishListDto(wishListDtoList)
                .build();

        // 응답
        return wishGetMyListResponseDto;
    }
}
