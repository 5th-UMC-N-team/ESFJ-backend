package umc.nteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.nteam.domain.Wish;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
