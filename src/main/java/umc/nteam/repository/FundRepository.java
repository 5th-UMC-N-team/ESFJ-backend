package umc.nteam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.nteam.domain.Fund;
import umc.nteam.domain.Wish;

public interface FundRepository extends JpaRepository<Fund, Long> {

    List<Fund> findAllByWish(Wish wish);

    List<Fund> findAllByWishId(Long wishId);
}
