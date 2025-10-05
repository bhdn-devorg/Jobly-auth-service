package dev.bhdn.jobly_auth_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "languages")
@Data
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
}
