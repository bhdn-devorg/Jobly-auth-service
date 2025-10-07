package dev.bhdn.jobly_auth_service.repository;

import dev.bhdn.jobly_auth_service.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
