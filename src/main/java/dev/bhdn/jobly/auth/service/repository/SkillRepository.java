package dev.bhdn.jobly.auth.service.repository;

import dev.bhdn.jobly.auth.service.model.Skill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findById(Long id);
}
