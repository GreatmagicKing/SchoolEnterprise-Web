package com.school.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.school.entity.AcceptanceRate;
import com.school.entity.InterviewR;
import com.school.entity.QuestionList;
import com.school.entity.Recruit;
import com.school.entity.Response;
import com.school.entity.StudentRate;
import com.school.entity.StudentScore;
import com.school.entity.User;
import com.school.entity.teacher.TeacherScore;
import com.school.service.HrService;
import com.school.service.StudentService;
import com.school.service.TeacherService;
import com.school.util.JwtDecoder;
import com.school.util.JwtTokenUtils;




/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理HR用户发来的请求
*/
@CrossOrigin
@RestController
public class HrController {

	@Autowired
	HrService hrService;
	@Autowired
	private StudentService studentService;
	
	
	String msg;
	String state;
	
	
	
	/** * 获取学生成绩，和评级
	* @param 
	* @return response
	*/
	@PostMapping(value = "/getStudentRate")
	public Response<List<StudentRate>> getStudentRate(HttpServletRequest request) {
		Response<List<StudentRate>> response=new Response<List<StudentRate>>("200", "访问成功", hrService.getStudentRate());
		return response;
	}
	
	/** * 获取学生的详细信息（每次课程成绩，和线上测试成绩）
	* @param 
	* @return response
	*/
	@PostMapping("/getStudentDetails")
	public Response<List<StudentScore>> getStudentDetails(@RequestBody User user,HttpServletRequest request) {
		
		Response<List<StudentScore>> response=
				new Response<List<StudentScore>>("200", "访问成功", studentService.getStudentScores(user.getUserId()));
		return response;
	}
	
	
	/** * 获取学生提出的问题
	* @param 
	* @return response
	*/
	@PostMapping("/getHrQuestionList")
	public Response<List<QuestionList>> getHrQuestionList(@RequestBody User user,HttpServletRequest request) {
		
		Response<List<QuestionList>> response=new Response<List<QuestionList>>("200", "访问成功", hrService.getHrQuestionList(user.getUserId()));
		return response;
	}
	
	
	
	/** * 获取学生回答的问题
	* @param 
	* @return response
	*/
	@PostMapping("/getHrAnswerList")
	public Response<List<QuestionList>> getHrAnswerList(@RequestBody User user,HttpServletRequest request) {
		
		Response<List<QuestionList>> response=new Response<List<QuestionList>>("200", "访问成功", hrService.getHrAnswerList(user.getUserId()));
		
		return response;
	}
	
	
	/** * 获取HR第一次面试的学生成绩
	* @param 
	* @return response
	*/
	@PostMapping("/setMyInterview")
	public Response<String> setMyInterview(@RequestBody Recruit recruit,HttpServletRequest request) {
		int row=hrService.setMyInterview(recruit);
		if(row!=0) {
			this.msg="编辑成功";
			this.state="200";
		}else {
			this.msg="编辑失败";
			this.state="1";
		}
		Response<String> response=new Response<String>("200", "访问成功", null);
		
		return response;
	}
	
	
	
	/** * 获取HR第一次面试的学生信息
	* @param 
	* @return response
	*/
	@PostMapping("/getMyInterview")
	public Response<List<Recruit>> getMyInterview(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {

		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", hrService.getMyInterview(userId));
		
		return response;
	}
	
	
	
	/** * 获取每一个需要面试的学生
	* @param 
	* @return response
	*/
	@PostMapping("/getEveryRecruit")
	public Response<List<Recruit>> getEveryRecruit(HttpServletRequest request) {
		
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", hrService.getEveryRecruit());
		
		return response;
	}
	
	
	
	/** * 获取通过第一次面试，进入第二次面试的学生
	* @param 
	* @return 
	*/
	@PostMapping("/getTwoRecruit")
	public Response<List<Recruit>> getTwoRecruit(HttpServletRequest request) {
		
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", hrService.getTwoRecruit());
		
		return response;
	}
	
	
	/** * 获取第一次面试学生的结果
	* @param 
	* @return response
	*/
	@PostMapping("/getHrRecruit")
	public Response<List<Recruit>> getHrRecruit(HttpServletRequest request) {
		
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", hrService.getHrRecruit());
		
		return response;
	}

	
	
	/** * 分配第一次面试的人员
	* @param 
	* @return response
	*/
	@PostMapping("/setHrRecruit")
	public Response<String> setHrRecruit(@RequestBody Recruit recruit,HttpServletRequest request) {
		int row=hrService.setHrRecruit(recruit);
		if(row!=0) {
			this.msg="编辑成功";
			this.state="200";
		}else {
			this.msg="编辑失败";
			this.state="1";
		}
		Response<String> response=new Response<String>(state,msg, null);
		
		return response;
	}
	
	
	/** * 获取教师二面试面试结果
	* @param 
	* @return 
	*/
	@PostMapping("/getTeacherRecruit")
	public Response<List<Recruit>> getTeacherRecruit(HttpServletRequest request) {
		
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", hrService.getTeacherRecruit());
		
		return response;
	}
	
	
	/** * 分配教师二面面试
	* @param 
	* @return 
	*/
	@PostMapping("/setTeacherRecruit")
	public Response<String> setTeacherRecruit(@RequestBody Recruit recruit,HttpServletRequest request) {
		int row=hrService.setTeacherRecruit(recruit);
		if(row!=0) {
			this.msg="编辑成功";
			this.state="200";
		}else {
			this.msg="编辑失败";
			this.state="1";
		}
		Response<String> response=new Response<String>(state, msg, null);
		
		return response;
	}
	
	
	/** * 获取面试最终信息
	* @param 
	* @return response
	*/
	@PostMapping("/getInterviewR")
	public Response<List<InterviewR>> getInterviewR(HttpServletRequest request) {
		
		Response<List<InterviewR>> response=new Response<List<InterviewR>>("200", "访问成功", hrService.getInterviewR());
		
		return response;
	}
	
	
	/** * 编辑面试结果
	* @param 
	* @return response
	*/
	@PostMapping("/setInterviewR")
	public Response<String> setInterviewR(@RequestBody InterviewR interviewR,HttpServletRequest request) {
		int row=hrService.setInterviewR(interviewR);
		if(row!=0) {
			this.msg="编辑成功";
			this.state="200";
		}else {
			this.msg="编辑失败";
			this.state="1";
		}
		Response<String> response=new Response<String>(state, msg, null);
		
		return response;
	}
	/** * 返回用户列表
	* @param 
	* @return response
	*/
	@PostMapping("/getUserList")
	public Response<List<User>> getUserList(HttpServletRequest request) {
		
		Response<List<User>> response=new Response<List<User>>("200", "访问成功", hrService.getUserList());
		return response;
	}
	
	/** * 插入用户
	* @param 
	* @return response
	*/
	@PostMapping("/setUserList")
	public Response<String> setUserList(@RequestBody User user,HttpServletRequest request) {
		int row=hrService.setUserList(user);
		if(row!=0) {
			this.msg="编辑成功";
			this.state="200";
		}else {
			this.msg="编辑失败";
			this.state="1";
		}
		Response<String> response=new Response<String>("200", "访问成功", null);
		return response;
	}
	/** * 获取成绩平均值
	* @param 
	* @return response
	*/
	@PostMapping("/getAverageScore")
	public Response<List<StudentScore>> getAverageScore(HttpServletRequest request) {
		Response<List<StudentScore>> response=new Response<List<StudentScore>>("200", "访问成功", hrService.getAverageScore());
		return response;
	}
	/** * 获取录取率
	* @param 
	* @return response
	*/
	@PostMapping("/getAcceptanceRate")
	public Response<List<AcceptanceRate>> getAcceptanceRate(HttpServletRequest request) {
		Response<List<AcceptanceRate>> response=new Response<List<AcceptanceRate>>("200", "访问成功", hrService.getAcceptanceRate());
		return response;
	}
}
