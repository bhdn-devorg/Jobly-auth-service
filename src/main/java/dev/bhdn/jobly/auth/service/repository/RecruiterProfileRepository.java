package dev.bhdn.jobly.auth.service.repository;

import dev.bhdn.jobly.auth.service.model.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Long> {
}
