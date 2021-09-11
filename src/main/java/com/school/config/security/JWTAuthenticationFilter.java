package com.school.config.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import javax.annotation.Resource;
import javax.servlet.FilterChain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.Response;
import com.school.entity.User;
import com.school.util.JwtTokenUtils;
import com.school.util.RandomString;
import com.school.util.RedisUtil;


//@Component
//public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	
//	@Resource
//	private RedisUtil redisUtil;
//    private AuthenticationManager authenticationManager;
//    
//    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException {
//
//        // 从输入流中获取到登录的信息
//        try {
//        	User loginUser = new ObjectMapper().readValue(request.getInputStream(), User.class);
//        	
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginUser.getUserId(), loginUser.getPassword(), new ArrayList<>())
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//
//    	
//
//		// 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
//		// 所以就是JwtUser啦
//        User jwtUser = (User) authResult.getPrincipal();
////        System.out.println("jwtUser:" + jwtUser.toString());
////        System.out.println("jwtUser:" + jwtUser.getRole());
//        String token = JwtTokenUtils.createToken(jwtUser.getUserId(), jwtUser.getRole(),false);
//        String randomString=RandomString.RandomS(10);
//        System.out.println(jwtUser.getUserId());
//        System.out.println(randomString);
////        boolean b=redisUtil.set(jwtUser.getUserId(), randomString);
////        System.out.println(b);
////        String token = JwtTokenUtils.createToken(jwtUser.getUserId(), jwtUser.getRole(),randomString,false);
//        
//        // 返回创建成功的token
//        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
//        PrintWriter writer = response.getWriter();
//        Response<String > responseR=new Response<String>("0", "登录成功",jwtUser.getRole());
//        writer.write("{\"state\":\"" + responseR.getState() + "\",\"msg\":\"" + responseR.getMsg() + "\",\"data\":\"" + responseR.getData() + "\",\"token\":\"" + JwtTokenUtils.TOKEN_PREFIX + token +"\"}");
//    }

	// 这是验证失败时候调用的方法
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
//    }
//}

