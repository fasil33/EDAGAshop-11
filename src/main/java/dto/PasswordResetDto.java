package dto;

import lombok.Data;

@Data
public class PasswordResetDto {
    private String email;
    private String password;
    private String password2;
}
