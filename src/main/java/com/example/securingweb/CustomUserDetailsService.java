package com.example.securingweb;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	public static String passwordEncoder(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		UserEntity user1 = new UserEntity();
		List<Roles> roles = new ArrayList<>();
		Roles role1 = new Roles();
		role1.setName("ADMIN");
		roles.add(role1);
		
		String password = "123";
		String encryptedPassword = passwordEncoder(password);
		
		user1.setUsername("user");
		user1.setPassword(encryptedPassword);
		user1.setRoles(roles);

		
		userRepository.save(user1);

		UserEntity user = userRepository.findByUsername(username);

		if (user != null) {
			User authUser = new User(
					user.getUsername(), 
					user.getPassword(), 
					user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList())
					);
			return authUser;
		} else {
			throw new UsernameNotFoundException("Invalid username or password");
		}

	}
}