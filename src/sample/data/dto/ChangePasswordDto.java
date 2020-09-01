package sample.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangePasswordDto {
    public String oldPassword;
    public String newPassword;
    public String confirmNewPassword;
}
