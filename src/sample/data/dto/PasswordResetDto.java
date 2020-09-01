package sample.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PasswordResetDto {
    public String emailAddress;
    public String password;
    public String otp;
}
