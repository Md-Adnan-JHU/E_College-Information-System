package com.adnan.controller;

import com.adnan.dto.MessageResponse;
import com.adnan.dto.SignUpRequest;
import com.adnan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping
	MessageResponse createNewUser(@RequestBody SignUpRequest signUpRequest) {
		return service.createNewUser(signUpRequest);
	}

//	@PostMapping("/change-password")
//	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
//	MessageResponse changePassword(@RequestBody ChangePasswordDTO requestBody) {
//		return service.changePassword(requestBody);
//	}
//
//	@PostMapping("/verify-otp")
//	MessageResponse verifyOtp(@RequestBody OTPRequest requestBody) {
//		return service.verifyOtp(requestBody);
//	}

	
	
	
}
