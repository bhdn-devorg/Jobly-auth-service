package dev.bhdn.jobly.auth.service.dto.user;

import dev.bhdn.jobly.auth.service.model.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private String fullName;
    private String email;
    private String password;
    private String repeatPassword;
    private User.AccountType accountType;
}
