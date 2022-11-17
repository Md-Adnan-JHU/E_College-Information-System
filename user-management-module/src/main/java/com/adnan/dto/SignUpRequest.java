package com.adnan.dto;


import com.adnan.enums.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {

	private String email;
	private String userName;
	private String phoneNo;
	private String password;
	private Gender gender;
	private Date dateOfBirth;
	
}
