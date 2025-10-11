package dev.bhdn.jobly.auth.service.repository;

import dev.bhdn.jobly.auth.service.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
