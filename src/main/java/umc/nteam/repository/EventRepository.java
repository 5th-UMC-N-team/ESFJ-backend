package umc.nteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.nteam.domain.Event;
import umc.nteam.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByUser(User user);

    @Query("SELECT e FROM Event e " +
            "WHERE YEAR(e.date) = :year " +
            "AND MONTH(e.date) = :month " +
            "AND (e.user = :user OR e.user IN (SELECT f FROM User u JOIN u.friendList f WHERE u = :user))")
    List<Event> findAllByUserAndMonth(@Param("user") User user,
                                      @Param("year") int year,
                                      @Param("month") int month);
}
