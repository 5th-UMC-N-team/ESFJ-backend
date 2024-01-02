package umc.nteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.nteam.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
