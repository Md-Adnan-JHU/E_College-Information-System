package com.adnan.security.jwt;

import com.adnan.Repository.UserRepository;
import com.adnan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userDao;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
		// username is taken Id since username and email can be changed
		if(username==null)
			username = "0";
		Long id = Long.parseLong(username);
		User user = userDao.findById(id).orElse(null);
		

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getId()+"", user.getUserPassword(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return authorities;
	}

}
