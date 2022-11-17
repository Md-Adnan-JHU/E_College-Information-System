package com.adnan.serviceImpl;

import com.adnan.Repository.UserRepository;
import com.adnan.dto.LoginRequest;
import com.adnan.dto.LoginResponse;
import com.adnan.dto.MessageResponse;
import com.adnan.dto.OTPRequest;
import com.adnan.entity.User;
import com.adnan.security.jwt.JwtUtil;
import com.adnan.security.jwt.UserDetailService;
import com.adnan.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserDetailService userDetailService;
	
	
	@Override
	public LoginResponse loginUser(LoginRequest loginRequest) {
	
		User user = userRepo.findByUsername(loginRequest.getUsername());
		if(user==null) {
			user = userRepo.findByEmail(loginRequest.getUsername());
			if(user==null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email does not exist!");
			}
		}
		
		if(!encoder.matches(loginRequest.getPassword(), user.getUserPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Password!");
		}
		
		UserDetails userDetails = userDetailService.loadUserByUsername(user.getId()+"");
		
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setJwtToken(jwtUtil.generateToken(userDetails));
		loginResponse.setRole(user.getRole());
		loginResponse.setUsername(user.getUsername());
		
		return loginResponse;
	}


	@Override
	public MessageResponse loginAdminUser(LoginRequest loginRequest) {
	
		User user = userRepo.findByUsername(loginRequest.getUsername());
		if(user==null) {
			user = userRepo.findByEmail(loginRequest.getUsername());
			if(user==null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Email does not exist!");
			}
		}
		
		
		if(!encoder.matches(loginRequest.getPassword(), user.getUserPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Password!");
		}
		
        return MessageResponse.builder().message("Otp Sent Successfuly !").build();
		
	}


	@Override
	public LoginResponse verifyOtpForAdmin(OTPRequest requestBody) {

		User user = userRepo.findByUsername(requestBody.getEmail());
		if(user==null) {
			user = userRepo.findByEmail(requestBody.getEmail());
			if(user==null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Email does not exist!");
			}
		}


		UserDetails userDetails = userDetailService.loadUserByUsername(user.getId()+"");

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setJwtToken(jwtUtil.generateToken(userDetails));
		loginResponse.setRole(user.getRole());
		loginResponse.setUsername(user.getUsername());

		return loginResponse;
	}
	
	
	
}
