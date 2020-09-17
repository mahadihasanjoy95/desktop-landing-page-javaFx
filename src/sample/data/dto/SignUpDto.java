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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SignUpDto signUpDto = (SignUpDto) o;
        return firstName.equals(signUpDto.firstName) &&
                lastName.equals(signUpDto.lastName) &&
                userName.equals(signUpDto.userName) &&
                emailAddress.equals(signUpDto.emailAddress) &&
                password.equals(signUpDto.password) &&
                phoneNumber.equals(signUpDto.phoneNumber) &&
                country.equals(signUpDto.country) &&
                address.equals(signUpDto.address) &&
                city.equals(signUpDto.city) &&
                state.equals(signUpDto.state) &&
                zipCode.equals(signUpDto.zipCode);
    }
}


