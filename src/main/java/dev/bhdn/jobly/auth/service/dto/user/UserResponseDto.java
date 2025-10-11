package dev.bhdn.jobly.auth.service.dto.user;

import dev.bhdn.jobly.auth.service.model.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private User.AccountType accountType;
}
