package com.demoDigital.demo.controllers;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api") // group4/
@CrossOrigin(origins = "*")
public class AuthController {

//	@Autowired
//	UserRepository userService;

	/**
	 * after ADFS Login succesfully, call this API to see loggedInUser info
	 * @param principal
	 * @return
	 */
	@GetMapping("/loggedInUser")
	public Map<String, Object> getUserInfo(@AuthenticationPrincipal OidcUser principal) {

		System.out.println(principal.getClaims().toString());

		Map<String, String> map = new Hashtable<String, String>();
		map.put("user_name", principal.getClaimAsString("samaccountname"));
		map.put("organization", principal.getClaimAsString("department"));
		map.put("first_name", principal.getClaimAsString("firstname"));
		map.put("last_name", principal.getClaimAsString("lastname"));
		map.put("email", principal.getClaimAsString("email"));
//		unique_name=APAC\MIH1HC
		return Collections.unmodifiableMap(map);
	}
	
	@GetMapping("/loggedInUserFake")
	public Map<String, Object> getUserInfoFake() {
		Map<String, String> map = new Hashtable<String, String>();
		map.put("user_name", "Hoang Thuong");
		map.put("organization", "Hoang Thuong");
		map.put("first_name", "Thuong");
		map.put("last_name", "Hoang");
		map.put("email", "hhthuonghcmus@gmail.com");
//		unique_name=APAC\MIH1HC
		return Collections.unmodifiableMap(map);
	}
	
	@GetMapping("/oidc-user")
	public OidcUser get(@AuthenticationPrincipal OidcUser principal) {
		return principal;
	}
	
//	
}