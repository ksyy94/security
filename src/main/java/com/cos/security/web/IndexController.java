package com.cos.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security.auth.PrincipalDetails;
import com.cos.security.domain.user.User;
import com.cos.security.domain.user.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		return "index";
		
	}
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
		
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	//username, password, email	
	@PostMapping("/joinProc")
	public String joinProc(User user) {
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setRole("ROLE_USER");
		user.setPassword(encPassword);
		//UserRepository 만들어서 save()
		userRepository.save(user);
		return "index";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String user(@AuthenticationPrincipal PrincipalDetails principal) {
		return "user 페이지 : "+principal.getUser().getEmail();
		
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager(@AuthenticationPrincipal PrincipalDetails principal) {
		return "manager 페이지 : "+principal.getUser().getEmail();
		
	}
	@GetMapping("/admin")
	@ResponseBody
	public String admin(@AuthenticationPrincipal PrincipalDetails principal) {
		return "admin 페이지 : "+principal.getUser().getEmail();
		
	}
	
	
}
