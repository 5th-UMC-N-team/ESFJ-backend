package umc.nteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.nteam.domain.Fund;

public interface FundRepository extends JpaRepository<Fund, Long> {
}
