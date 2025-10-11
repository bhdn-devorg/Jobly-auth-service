package dev.bhdn.jobly.auth.service.repository;

import dev.bhdn.jobly.auth.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
