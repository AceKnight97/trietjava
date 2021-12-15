package com.demoDigital.demo.services.impl;

import com.demoDigital.demo.repository.UserRepository;
import com.demoDigital.demo.model.User;
import com.demoDigital.demo.model.PersonalInfo;
import com.demoDigital.demo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.security.SecureRandom;
import static com.demoDigital.demo.model.Constants.SIGNING_KEY;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepo;

	// @Autowired
	// private BCryptPasswordEncoder bcryptEncoder;

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

	public String getNewSalt() throws Exception {
		// Don't use Random!
		SecureRandom random = SecureRandom.getInstance(SIGNING_KEY);
		// NIST recommends minimum 4 bytes. We use 8.
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	public String getEncryptedPassword(String password, String salt) throws Exception {
		String algorithm = "PBKDF2WithHmacSHA1";
		int derivedKeyLength = 160; // for SHA1
		int iterations = 20000; // NIST specifies 10000

		byte[] saltBytes = Base64.getDecoder().decode(salt);
		KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		byte[] encBytes = f.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(encBytes);
	}

	@Override
	public User save(PersonalInfo user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		// String salt;
		// String encryptedPassword;
		// String pas = user.getPassword();
		// try {
		// salt = getNewSalt();
		// encryptedPassword = getEncryptedPassword(pas, salt);
		// newUser.setPassword(encryptedPassword);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepo.save(newUser);
	}
}
