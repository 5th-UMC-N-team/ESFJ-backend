package umc.nteam.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.nteam.auth.AuthUser;
import umc.nteam.converter.EventConverter;
import umc.nteam.domain.Event;
import umc.nteam.domain.User;
import umc.nteam.dto.EventDto;
import umc.nteam.service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    
    @GetMapping("/{friendId}")
    public ResponseEntity<EventDto.FriendEventListDto> showFriendEventList(@PathVariable("friendId") Long friendId) {
        List<Event> eventList = eventService.findAllByUserId(friendId);
        EventDto.FriendEventListDto body = new EventConverter().toFriendEventListDto(friendId, eventList);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/")
    @Parameters({
        @Parameter(name = "year", description = "이벤트를 확인할 년도"),
        @Parameter(name = "month", description = "이벤트를 확인할 월")
    })
    public ResponseEntity<EventDto.MonthlyEventResponseDto> showEventListByMonth(@RequestParam("year") int year, @RequestParam("month") int month, @AuthUser User user) {
        List<Event> eventList = eventService.findAllByUserAndMonth(user, year, month);
        EventDto.MonthlyEventResponseDto body = new EventConverter().toMonthlyEventResponseDto(eventList);
        return ResponseEntity.ok(body);
    }
}
