package sample.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {
    public Long userId;
    public String userName;
    public String firstName;
    public String lastName;
    public String emailAddress;
    public String phoneNumber;
    public String clientId;
    public String licenseNumber;
    public String country;
    public String address;
    public String city;
    public String state;
    public String zipCode;
    public String registeredBy;
    public String roleId;
    public String roleDisplayName;
    public String photo;
}
