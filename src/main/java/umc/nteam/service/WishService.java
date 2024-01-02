package umc.nteam.service;

import java.io.IOException;
import umc.nteam.domain.Wish;
import umc.nteam.web.dto.WishDto.WishAddRequestDto;

public interface WishService {

    Wish createWish(WishAddRequestDto requestDto) throws IOException;
}
