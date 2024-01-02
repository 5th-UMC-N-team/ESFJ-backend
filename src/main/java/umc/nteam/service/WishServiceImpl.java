package umc.nteam.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import umc.nteam.domain.User;
import umc.nteam.domain.Wish;
import umc.nteam.repository.UserRepository;
import umc.nteam.repository.WishRepository;
import umc.nteam.dto.WishDto;

import java.util.ArrayList;
import java.util.List;
import umc.nteam.dto.WishDto.WishAddRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final FundService fundService;

    // 내 위시리스트 가져오기
    @Override
    public WishDto.WishGetMyListResponseDto getMyList(User user, int priceRange) {
        // 내 위시 리스트 가져오기
        List<WishDto.WishListDto> wishListDtoList = getWishList(user.getId(), priceRange);

        // 최종 Response Dto 생성

        // 응답
        return WishDto.WishGetMyListResponseDto.builder()
            .profileUrl(user.getProfileUrl())
            .wishListDto(wishListDtoList)
            .build();
    }

    // 선택한 친구의 위시리스트 가져오기
    @Override
    public WishDto.WishGetFriendListResponseDto getFriendList(Long friendId, int priceRange) {
        // 친구의 위시 리스트 가져오기
        List<WishDto.WishListDto> wishListDtoList = getWishList(friendId, priceRange);

        // 친구 찾기
        User friend = userRepository.findById(friendId)
            .orElseThrow(() -> new RuntimeException("해당 id에 해당하는 사용자가 없습니다."));

        // 응답
        return WishDto.WishGetFriendListResponseDto.builder()
            .userId(friendId)
            .profileUrl(friend.getProfileUrl())
            .name(friend.getName())
            .wishListDto(wishListDtoList)
            .build();
    }

    @Override
    public Wish createWish(User user, MultipartFile file, WishAddRequestDto requestDto) throws IOException {
        String imageUrl = putFileToS3(file);
        Wish wish = Wish.builder()
            .user(user)
            .name(requestDto.getName())
            .price(requestDto.getPrice())
            .reason(requestDto.getReason())
            .link(requestDto.getLink())
            .imageUrl(imageUrl)
            .fundWishStatus(requestDto.getFundWishStatus())
            .build();

        return wishRepository.save(wish);
    }

    private String putFileToS3(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
        return "https://esfj.s3.ap-northeast-2.amazonaws.com/" + fileName;
    }

    // DB에서 위시리스트 가져오는 메소드
    private List<WishDto.WishListDto> getWishList(Long userId, int priceRange) {
        // 로그인한 유저의 전체 위시 리스트 가져오기
        List<Wish> userWishList = wishRepository.findAllByUserId(userId);

        // Dto 리스트에 담기
        List<WishDto.WishListDto> wishListDtoList = new ArrayList<>();
        for (Wish wish : userWishList) {
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
        return wishListDtoList;
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

    // 위시 모금현황 가져오기
    @Override
    public WishDto.WishGetDetailResponseDto getDetail(Long wishId){
        // 위시 찾기
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new RuntimeException("해당 id에 해당하는 위시가 없습니다."));
        // 모금현황 찾기
        int fundPrice = fundService.getFundSum(wish);

        return WishDto.WishGetDetailResponseDto.builder()
                .wishId(wishId)
                .name(wish.getName())
                .price(wish.getPrice())
                .reason(wish.getReason())
                .link(wish.getReason())
                .imageUrl(wish.getImageUrl())
                .fundPrice(fundPrice)
                .percentage((float) fundPrice /wish.getPrice() * 100)
                .build();
    }
}
