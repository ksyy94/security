package com.cos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 시큐리티 설정파일을 활성화 원래 설정이 이파일로 바뀜
@Configuration // IoC 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean //메서드의 리턴값을 IoC에 등록하는것
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.csrf().disable(); // 기억 : form태그 요청만 가능한 것을 비활성화한다.
		
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated()//user를통해서 들어오는 것들 이외의 모든 요청 허용 인증필요
		.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') ")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') ")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/loginForm")
		.loginProcessingUrl("/loginProc")
		.defaultSuccessUrl("/")
		.and()
		.logout()
		.logoutSuccessUrl("/logoutProc");
		
	}

}
