package dev.bhdn.jobly.auth.service.repository;

import dev.bhdn.jobly.auth.service.model.EmployeeProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
    Optional<EmployeeProfile> findById(Long id);
}
