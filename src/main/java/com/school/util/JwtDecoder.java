package com.school.util;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.CheckUser;

/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 用于给tokenHeader解码，返回用户ID
*/
public class JwtDecoder {

	static final Base64.Decoder decoder = Base64.getDecoder();
	public static String ToDecodoer(String tokenHeader) throws JsonMappingException, JsonProcessingException {
		String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
		String[] tokenPayload=token.split("\\.");
	    String Payload=new String(decoder.decode(tokenPayload[1]));
	    CheckUser checkUser = new ObjectMapper().readValue(Payload,CheckUser.class);
		return checkUser.getSub();
	}
	
}
