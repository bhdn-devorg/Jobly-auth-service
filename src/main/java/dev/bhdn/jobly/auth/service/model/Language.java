package dev.bhdn.jobly.auth.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "languages")
@Data
@SQLDelete(sql = "UPDATE languages SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
