package dev.bhdn.jobly_auth_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    public enum AccountType {
        EMPLOYEE,
        RECRUITER;
    }
}
