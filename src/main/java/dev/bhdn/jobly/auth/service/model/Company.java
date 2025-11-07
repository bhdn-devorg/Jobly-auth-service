package dev.bhdn.jobly.auth.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE companies SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Accessors(chain = true)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "logo_link")
    private String logoLink;

    @Column(name = "website_link")
    private String websiteLink;

    @Column(name = "linkedin_link")
    private String linkedinLink;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
