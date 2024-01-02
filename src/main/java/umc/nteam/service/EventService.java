package umc.nteam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import umc.nteam.domain.Event;
import umc.nteam.domain.User;
import umc.nteam.repository.EventRepository;
import umc.nteam.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public List<Event> findAllByUserAndMonth(User user, int year, int month) {
        return eventRepository.findAllByUserAndMonth(user, year, month);
    }
}
