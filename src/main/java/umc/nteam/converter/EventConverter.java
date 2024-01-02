package umc.nteam.converter;

import umc.nteam.domain.Event;
import umc.nteam.dto.EventDto;

import java.util.List;

public class EventConverter {
    public EventDto.MonthlyEventResponseDto toMonthlyEventResponseDto(List<Event> eventList) {
        List<EventDto.EventListDto> eventListDto = eventList.stream().map(event -> EventDto.EventListDto.builder()
                .name(event.getName())
                .date(event.getDate())
                .eventId(event.getId())
                .build()
        ).toList();
        return new EventDto.MonthlyEventResponseDto(eventListDto);
    }
}
