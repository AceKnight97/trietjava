package com.demoDigital.demo.services.impl;

import com.demoDigital.demo.repository.UserRepository;
import com.demoDigital.demo.model.User;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

	@Override
	public PersonalInfo update(PersonalInfo userDto) {
		User user = findById(userDto.getId());
		if (user != null) {
			BeanUtils.copyProperties(userDto, user, "password");
			userRepo.save(user);
		}
		return userDto;
	}

	@Override
	public User save(PersonalInfo user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepo.save(newUser);
	}
}
