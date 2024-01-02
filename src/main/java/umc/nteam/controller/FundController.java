package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.nteam.dto.FundDto;
import umc.nteam.dto.SuccessDto;

@RestController
@RequiredArgsConstructor
public class FundController {

    @PostMapping("/fund/{wishId}")
    public ResponseEntity<SuccessDto> fund(@PathVariable Long wishId, @RequestBody FundDto.FundRequestDto fundRequestDto) {
        return ResponseEntity.ok(null);
    }
}
