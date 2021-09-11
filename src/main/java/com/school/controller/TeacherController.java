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
import com.school.entity.InterviewR;
import com.school.entity.QuestionList;
import com.school.entity.Recruit;
import com.school.entity.Response;
import com.school.entity.TeacherCourse;
import com.school.entity.Test;
import com.school.entity.TestProblem;
import com.school.entity.User;
import com.school.entity.teacher.TeacherScore;
import com.school.service.TeacherService;
import com.school.util.JwtDecoder;
import com.school.util.JwtTokenUtils;




/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理教师用户发来的请求
*/
@CrossOrigin
@RestController
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	String msg;
	String state;
	
	
	
	/** * 教师获取自己教学的课程，并返回所要编辑的学生成绩列表
	* @param 
	* @return 返回所要编辑课程成绩的信息
	*/
	@PostMapping("/getTeacherCourses")
	public Response<List<TeacherScore>> getTeacherCourses(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
//		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
//		String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
//		String userId = JwtTokenUtils.getUsername(token);
		Response<List<TeacherScore>> response=new Response<List<TeacherScore>>("200", "访问成功", teacherService.getTeacherScores(userId));
		return response;
	}
	
	
	/** * 教师编辑学生课程成绩
	* @param teacherScore 学生id和学生课程成绩
	* @return 返回response
	*/
	@PostMapping("/setTeacherCourses")
	public Response<String> setTeacherCourses(@RequestBody TeacherScore teacherScore,HttpServletRequest request) {
		int row=teacherService.setTeacherScores(teacherScore);
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
	
	
	/** * 教师获取问题列表
	* @param 
	* @return 返回问题列表
	*/
	@PostMapping("/getTeacherQuestion")
	public Response<List<QuestionList>> getTeacherQuestion(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<QuestionList>> response=new Response<List<QuestionList>>("200", "访问成功", teacherService.getTeacherQuestion(userId));
		return response;
	}
	
	
	
	/** * 教师获取自己课程的线上测试
	* @param 
	* @return 返回线上测试
	*/
	@PostMapping("/getTeacherTest")
	public Response<List<Test>> getTeacherTest(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<Test>> response=new Response<List<Test>>("200", "访问成功", teacherService.getTeacherTest(userId));
		return response;
	}
	
	
	/** * 教师获取学生用户的线上测试成绩
	* @param 
	* @return 返回学生线上测试成绩
	*/
	@PostMapping("/showTestScore")
	public Response<List<TeacherScore>> showTestScore(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<TeacherScore>> response=new Response<List<TeacherScore>>("200", "访问成功", teacherService.showTestScore(userId));
		return response;
	}
	
	
	
	/** * 教师发布线上测试
	* @param 
	* @return 返回response
	*/
	@PostMapping("/releaseTest")
	public Response<String> releaseTest(@RequestBody Test test,HttpServletRequest request) {
		int row=teacherService.releaseTest(test.getTestId());
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
	
	
	
	/** * 教师取消发布线上测试
	* @param 
	* @return 返回response
	*/
	@PostMapping("/revokeTest")
	public Response<String> revokeTest(@RequestBody Test test) {
		int row=teacherService.revokeTest(test.getTestId());
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
	
	
	
	/** * 教师获取HR分配的学生面试信息
	* @param 
	* @return 返回第二次面试信息
	*/
	@PostMapping("/getTeacherInterview")
	public Response<List<Recruit>> getTeacherInterview(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", teacherService.getTeacherInterview(userId));
		
		return response;
	}
	
	
	/** * 教师编辑第二次面试成绩
	* @param recruit 面试者信息
	* @return 返回response
	*/
	@PostMapping("/setTeacherInterview")
	public Response<String> setTeacherInterview(@RequestBody Recruit recruit,HttpServletRequest request) {
		int row=teacherService.setTeacherInterview(recruit);
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
	/** * 插入测试信息
	* @param 
	* @return 返回response
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	*/
	@PostMapping("/setTestList")
	public Response<String> setTestList(@RequestBody Test test,HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		int row=teacherService.setTestList(test,userId);
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
	/** * 插入测试题目
	* @param 
	* @return 返回response
	*/
	@PostMapping("/setTestDetail")
	public Response<String> setTestDetail(@RequestBody TestProblem testProblem,HttpServletRequest request) {
		int row=teacherService.setTestDetail(testProblem);
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
	/** * 获取测试题目
	* @param 
	* @return 返回response
	*/
	@PostMapping("/getTestProblem")
	public Response<List<TestProblem>> getTestProblem(@RequestBody Test test,HttpServletRequest request) {
		Response<List<TestProblem>> response=new Response<List<TestProblem>>("200", "访问成功", teacherService.getTestProblem(test));
		return response;
	}
	/** * 获取教师课程
	* @param 
	* @return 返回response
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	*/
	@PostMapping("/getTeacherCourse")
	public Response<TeacherCourse> getTeacherCourse(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<TeacherCourse> response=new Response<TeacherCourse>("200", "访问成功", teacherService.getTeacherCourse(userId));
		return response;
	}
}
