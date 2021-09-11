package com.school.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.entity.Response;
import com.school.entity.User;
import com.school.service.UserService;


@RestController
@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})
public class LoginController {
	@PostMapping("/success")
	public Response<List<GrantedAuthority>> success(HttpServletRequest request) {
		
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		Response<List<GrantedAuthority> > response=new Response<List<GrantedAuthority>>("0", "登录成功", authorities);
		return response;
	}
	@PostMapping("/failure")
	public Response<String> failure(HttpServletRequest request) {
		Response<String > response=new Response<String>("1", "登录失败", null);
		return response;
	}
}
