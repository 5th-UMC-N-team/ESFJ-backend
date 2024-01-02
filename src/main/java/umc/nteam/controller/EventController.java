package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.nteam.dto.EventDto;
import umc.nteam.dto.SuccessDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    @PostMapping
    public ResponseEntity<SuccessDto> createEvent(@RequestBody EventDto.EventCreateDto eventCreateDto) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<SuccessDto> updateEvent(@RequestBody EventDto.EventUpdateDto eventUpdateDto, @PathVariable("eventId") Long eventId) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<SuccessDto> deleteEvent(@PathVariable("eventId") Long eventId) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{friendId}")
    public ResponseEntity<EventDto.FriendEventListDto> showFriendEventList(@PathVariable("friendId") Long friendId) {
        return ResponseEntity.ok(null);
    }

}
