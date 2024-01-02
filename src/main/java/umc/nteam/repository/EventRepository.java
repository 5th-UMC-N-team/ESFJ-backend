package umc.nteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.nteam.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
