package umc.nteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.nteam.domain.MemberFund;

public interface MemberFundRepository extends JpaRepository<MemberFund, Long> {
}
