package com.adnan.service;

import com.adnan.dto.MessageResponse;
import com.adnan.dto.SignUpRequest;

public interface UserService {

	MessageResponse createNewUser(SignUpRequest signUpRequest);

//	MessageResponse changePassword(ChangePasswordDTO dto);

	
}
