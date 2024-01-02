package umc.nteam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.nteam.auth.AuthUser;
import umc.nteam.converter.EventConverter;
import umc.nteam.domain.Event;
import umc.nteam.domain.User;
import umc.nteam.dto.EventDto;
import umc.nteam.dto.SuccessDto;
import umc.nteam.service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
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

    @GetMapping("/")
    public ResponseEntity<EventDto.MonthlyEventResponseDto> showEventListByMonth(@RequestParam("year") int year, @RequestParam("month") int month, @AuthUser User user) {
        List<Event> eventList = eventService.findAllByUserAndMonth(user, year, month);
        EventDto.MonthlyEventResponseDto body = new EventConverter().toMonthlyEventResponseDto(eventList);
        return ResponseEntity.ok(body);
    }
}
