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
public class WishServiceImpl implements WishService{

    private final WishRepository wishRepository;

    // 내 위시리스트 가져오기
    @Override
    public WishDto.WishGetMyListResponseDto getMyList(User user, int priceRange){
        // 로그인한 유저의 전체 위시 리스트 가져오기
        List<Wish> userWishList = wishRepository.findAllByUserId(user.getId());
        int priceUpper = 0;



        // Dto 리스트에 담기
        List<WishDto.WishListDto> wishListDtoList = new ArrayList<>();
        for(Wish wish: userWishList){
            // 가격이 사용자가 선택한 범위에 속하면
            if (isPriceInRange(wish.getPrice(), priceRange)) {
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
        }

        // 최종 Response Dto 생성
        WishDto.WishGetMyListResponseDto wishGetMyListResponseDto = WishDto.WishGetMyListResponseDto.builder()
                .profileUrl(user.getProfileUrl())
                .wishListDto(wishListDtoList)
                .build();

        // 응답
        return wishGetMyListResponseDto;
    }

    // 가격이 사용자가 선택한 범위에 속하는지 확인하는 메서드
    private boolean isPriceInRange(int price, int priceRange) {
        return switch (priceRange) {
            case 1 -> price < 10000;    // 천원대
            case 2 -> price >= 10000 && price < 20000;  // 1만원대
            case 3 -> price >= 20000 && price < 30000;  // 2만원대
            case 4 -> price >= 30000 && price < 40000;  // 3만원대
            case 5 -> price >= 40000 && price < 50000;  // 4만원대
            case 6 -> price >= 50000;   // 5만원 이상
            // 다른 가격대에 대한 조건 추가 가능
            default -> true;    // 미선택 시 전체 다 보여줌
        };
    }
}
