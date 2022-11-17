package com.adnan.dto;

import lombok.Data;

@Data
public class LoginResponse {

	private String jwtToken;
	private String username;
	private String role;

}
