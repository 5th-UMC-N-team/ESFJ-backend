package umc.nteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.nteam.domain.EventNotification;

public interface EventNotificationRepository extends JpaRepository<EventNotification, Long> {
}
