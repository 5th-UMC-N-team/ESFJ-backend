package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FundController {

    @PostMapping("/fund/{wishId}")
    public ResponseEntity fund(@PathVariable Long wishId) {
        return ResponseEntity.ok(null);
    }
}
