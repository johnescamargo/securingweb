package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		UserEntity user1 = new UserEntity();
		user1.setUsername("user");
		user1.setPassword("ox0iCEPFqFKWKtRwOGcHXw==");
		
		userRepository.save(user1);

		UserEntity user = userRepository.findByUsername(username);

		if (user != null) {
			// THIS LINE BELOW IS WRONG
			UserDetails authUser = new User(user.getUsername(), user.getPassword(), null);
			return authUser;
		} else {
			throw new UsernameNotFoundException("Invalid username or password");
		}

	}
}