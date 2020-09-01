package sample.data.model;

import lombok.Data;

@Data
public class SignUpResponse {
	public String password;
	public String phoneNumber;
	public String gender;
	public String name;
	public String email;
	public int age;
}