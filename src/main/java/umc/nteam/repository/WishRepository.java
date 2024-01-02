package umc.nteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.nteam.domain.Wish;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    List<Wish> findAllByUserId(long userId);
}
