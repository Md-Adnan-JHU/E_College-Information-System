package com.adnan.service;

import com.adnan.dto.LoginRequest;
import com.adnan.dto.LoginResponse;
import com.adnan.dto.MessageResponse;
import com.adnan.dto.OTPRequest;

public interface UserAuthService {

	LoginResponse loginUser(LoginRequest loginRequest);

	MessageResponse loginAdminUser(LoginRequest loginRequest);

	LoginResponse verifyOtpForAdmin(OTPRequest requestBody);
	
}
