package umc.nteam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class EventDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventCreateDto {
        private LocalDate date;
        private String name;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventUpdateDto {
        private LocalDate date;
        private String name;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FriendEventListDto {
        private Long friendId;
        List<EventListDto> eventListDto;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventListDto {
        private Long eventId;
        private LocalDate date;
        private String name;
    }

}