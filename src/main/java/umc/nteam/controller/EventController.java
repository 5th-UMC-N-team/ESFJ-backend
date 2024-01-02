package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    @GetMapping
    public ResponseEntity createEvent() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{eventId}")
    public ResponseEntity updateEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity deleteEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{friendId}")
    public ResponseEntity showEventList(@PathVariable Long friendId) {
        return ResponseEntity.ok(null);
    }

}
