package dev.bhdn.jobly_auth_service.repository;

import dev.bhdn.jobly_auth_service.model.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Long> {
}
