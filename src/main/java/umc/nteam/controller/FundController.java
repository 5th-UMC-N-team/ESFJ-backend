package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.nteam.auth.AuthUser;
import umc.nteam.domain.User;
import umc.nteam.dto.FundDto;
import umc.nteam.dto.SuccessDto;
import umc.nteam.service.FundService;

@RestController
@RequiredArgsConstructor
public class FundController {

    private final FundService fundService;

    @PostMapping("/wishes/{wishId}")
    public ResponseEntity<SuccessDto> fund(@AuthUser User user, @PathVariable Long wishId, @RequestBody FundDto.FundRequestDto fundRequestDto) {
        fundService.fund(user, wishId, fundRequestDto);
        return ResponseEntity.ok(SuccessDto.builder().isSuccess(true).build());
    }
}
