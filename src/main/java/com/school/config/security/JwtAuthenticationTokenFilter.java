package com.school.config.security;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.CheckUser;
import com.school.entity.User;
import com.school.util.JwtTokenUtils;
import com.school.util.RedisUtil;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

	@Resource
	private RedisUtil redisUtil;
	
	final Base64.Decoder decoder = Base64.getDecoder();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
//      System.out.println(tokenHeader);
      // 如果请求头中没有Authorization信息则直接放行了
      if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
    	  filterChain.doFilter(request, response);
          return;
      }
      // 如果请求头中有token，则进行解析，并且设置认证信息
      SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
//      super.doFilterInternal(request, response, filterChain);
      filterChain.doFilter(request, response);
	}
	private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws JsonMappingException, JsonProcessingException {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");

//        String username = JwtTokenUtils.getUsername(token);
//        
//        String role = JwtTokenUtils.getUserRole(token);
//        System.out.println(token);
//        System.out.println(username);
//        System.out.println(role);
        String[] tokenPayload=token.split("\\.");
        String Payload=new String(decoder.decode(tokenPayload[1]));
        CheckUser checkUser = new ObjectMapper().readValue(Payload,CheckUser.class);
//        System.out.println(token);
//        System.out.println(checkUser);
//        System.out.println(checkUser.getSub());
        String secret= (String) redisUtil.get(checkUser.getSub());
//        System.out.println(secret);
        String username=JwtTokenUtils.getUsername(token, secret);
        String role=JwtTokenUtils.getUserRole(token, secret);
        
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
        }
        return null;
    }

}
