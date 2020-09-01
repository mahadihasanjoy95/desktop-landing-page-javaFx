package sample.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpDto {
    public String firstName;
    public String lastName;
    public String userName;
    public String emailAddress;
    public String password;
    public String phoneNumber;
    public String country;
    public String address;
    public String city;
    public String state;
    public String zipCode;
}
