package sample.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPValidationDto {
    private String emailAddress;
    private String otp;
}
