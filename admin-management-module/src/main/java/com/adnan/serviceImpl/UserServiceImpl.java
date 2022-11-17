package com.adnan.serviceImpl;

import com.adnan.Repository.UserRepository;
import com.adnan.dto.MessageResponse;
import com.adnan.dto.SignUpRequest;
import com.adnan.entity.User;
import com.adnan.enums.Roles;
import com.adnan.security.jwt.JwtUtil;
import com.adnan.service.UserService;
import com.adnan.utility.AppUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AppUtility appUtility;

	
	@Override
	public MessageResponse createNewUser(SignUpRequest signUpRequest) {
		
		User user = new User();
		
		if(userRepo.existsByEmail(signUpRequest.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exist!");
		}
		if(userRepo.existsByUsername(signUpRequest.getUserName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exist!");
		}
//		if(userRepo.existsByPhoneNo(signUpRequest.getPhoneNo())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone Number already exist!");
//		}
		
		user.setEmail(signUpRequest.getEmail());
		user.setPhoneNo(signUpRequest.getPhoneNo());
		user.setRole(Roles.USER.getRole());
		user.setUsername(signUpRequest.getUserName());
		user.setUserPassword(encoder.encode(signUpRequest.getPassword()));
		user.setDateOfBirth(signUpRequest.getDateOfBirth());
		user.setGender(signUpRequest.getGender().toString());
		user.setOtp(AppUtility.get6OTP());
		
		userRepo.save(user);
		
		String jwt = jwtUtil.generateToken(user);
		
		return MessageResponse.builder().message("User SignedUp Successfully!").data(jwt).build();
	}

//	@Override
//	public MessageResponse changePassword(ChangePasswordDTO dto) {
//		User user = appUtility.getCurrentUser();
//		if(!encoder.matches(dto.getOldPassword(), user.getUserPassword())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect old password!");
//		}
//		user.setUserPassword(encoder.encode(dto.getNewPassword()));
//		userRepo.save(user);
//		return MessageResponse.builder().message("Password Changed Successfully!").build();
//	}

}
