package umc.nteam.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.nteam.domain.Fund;
import umc.nteam.domain.MemberFund;
import umc.nteam.domain.User;
import umc.nteam.domain.Wish;
import umc.nteam.dto.FundDto;
import umc.nteam.repository.FundRepository;
import umc.nteam.repository.MemberFundRepository;
import umc.nteam.repository.WishRepository;

@Service
@RequiredArgsConstructor
public class FundService {

    private final FundRepository fundRepository;
    private final MemberFundRepository memberFundRepository;
    private final WishRepository wishRepository;

    @Transactional
    public Fund fund(User user, Long wishId, FundDto.FundRequestDto fundRequestDto) {
        Wish wish = wishRepository.findById(wishId).get();

        Fund fund = Fund.builder()
            .wish(wish)
            .fundPrice(fundRequestDto.getPrice())
            .build();



        MemberFund memberFund = MemberFund.builder()
            .user(user)
            .fund(fund)
            .price(fund.getFundPrice())
            .build();
        memberFundRepository.save(memberFund);
        return fundRepository.save(fund);

    }

    public int getFundSum(Wish wish) {
        int fundSum = 0;
        List<Fund> fundList = fundRepository.findAllByWish(wish);
        for (Fund fund : fundList) {
            fundSum += fund.getFundPrice();
        }
        return fundSum;
    }
}
