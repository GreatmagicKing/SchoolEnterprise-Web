package com.school.config.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.entity.User;
import com.school.service.UserService;



@Service
public class UserDetailServiceImpl implements UserDetailsService{

	
	
	
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userService.UserLogin(username);
//		System.out.println(username);
		if (user==null) {
			throw new UsernameNotFoundException("用户："+username+"不存在");
		}
		user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
		return user;
	}
	
	
	

}
