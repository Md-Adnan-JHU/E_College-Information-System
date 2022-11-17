package com.adnan.controller;

import com.adnan.dto.LoginRequest;
import com.adnan.dto.LoginResponse;
import com.adnan.dto.MessageResponse;
import com.adnan.dto.OTPRequest;
import com.adnan.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

	@Autowired
	private UserAuthService service;
	
	@PostMapping("/login")
	LoginResponse logInUser(@RequestBody LoginRequest loginRequest) {
		return service.loginUser(loginRequest);
	}
	
	@PostMapping("/admin/login")
	MessageResponse logInAdminUser(@RequestBody LoginRequest loginRequest) {
		return service.loginAdminUser(loginRequest);
	}
	
	@PostMapping("/verify-otp")
	LoginResponse verifyOtp(@RequestBody OTPRequest requestBody) {
		return service.verifyOtpForAdmin(requestBody);
	}
	
}
