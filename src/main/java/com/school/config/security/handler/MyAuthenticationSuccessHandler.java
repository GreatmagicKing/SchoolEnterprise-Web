package com.school.config.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.school.entity.Response;
import com.school.entity.User;
import com.school.util.JwtTokenUtils;
import com.school.util.RandomString;
import com.school.util.RedisUtil;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Resource
	private RedisUtil redisUtil;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
//		User userDetails = (User) SecurityContextHolder.getContext()
//			    .getAuthentication()
//			    .getPrincipal();
//		System.out.println(authentication);
//		System.out.println(userDetails);
//		
//		PrintWriter outPrintWriter = response.getWriter();
//		outPrintWriter.write(""+userDetails+"");
		
		// 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
				// 所以就是JwtUser啦
		User jwtUser = (User) authentication.getPrincipal();
//		System.out.println("jwtUser:" + jwtUser.toString());
//		System.out.println("jwtUser:" + jwtUser.getRole());
//		String token = JwtTokenUtils.createToken(jwtUser.getUserId(), jwtUser.getRole(),false);
		String randomString=RandomString.RandomS(10);
		System.out.println(jwtUser.getUserId());
		System.out.println(randomString);
		boolean b=redisUtil.set(jwtUser.getUserId(), randomString);
		System.out.println(b);
		String token = JwtTokenUtils.createToken(jwtUser.getUserId(), jwtUser.getRole(),randomString,false);
		        
		// 返回创建成功的token
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
		PrintWriter writer = response.getWriter();
		Response<String > responseR=new Response<String>("0", "登录成功",jwtUser.getRole());
		writer.write("{\"state\":\"" + responseR.getState() + "\",\"msg\":\"" + responseR.getMsg() + "\",\"data\":\"" + responseR.getData() + "\",\"token\":\"" + JwtTokenUtils.TOKEN_PREFIX + token +"\"}");
	}

}
