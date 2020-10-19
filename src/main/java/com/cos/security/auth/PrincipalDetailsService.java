package com.cos.security.auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security.domain.user.User;
import com.cos.security.domain.user.UserRepository;

//princpal : 접근주체 (인증주체)

@Service //ioc 등록 ioc에 등록될때 부모타입으로 등록됨
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("로그인 요청됨 : "+username);
		
		User user= userRepository.findByUsername(username);
		
		if(user == null){
			
			return null;
		
		}else {
		
			return new PrincipalDetails(user);
		}
	}
}
