package com.school.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.school.entity.Answer;
import com.school.entity.QuestionList;
import com.school.entity.QuestionsDetails;
import com.school.entity.Recruit;
import com.school.entity.Response;
import com.school.entity.StudentScore;
import com.school.entity.Test;
import com.school.entity.TestAnswer;
import com.school.entity.User;
import com.school.service.StudentService;
import com.school.util.JwtDecoder;
import com.school.util.JwtTokenUtils;

/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理学生用户发来的请求
*/
@RestController
@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})
public class StudentController {

	@Autowired
	private StudentService studentService;
	List<StudentScore> sScore;
	String msg;
	String state;
	
	/** * 学生用户获取学生的成绩
	* @param 
	* @return 返回学生成绩
	*/
	@PostMapping("/getStudentCourses")
	public Response<List<StudentScore>> getStudentCourses(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
//		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
//		String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
//		
//		String userId = JwtTokenUtils.getUsername(token);
		sScore=studentService.getStudentScores(userId);
		Response<List<StudentScore>> response=new Response<List<StudentScore>>("200", "访问成功", sScore);
		return response;
	}
	
	
	/** * 学生用户获取问题列表
	* @param 
	* @return 返回问题列表
	*/
	@PostMapping("/getQuestionList")
	public Response<List<QuestionList>> getQuestionList(HttpServletRequest request) {
		Response<List<QuestionList>> response=new Response<List<QuestionList>>("200", "访问成功", studentService.getQuestionList());
		return response;
	}
	
	
	/** * 学生用户写入问题
	* @param QuestionList 前端发回的问题体
	* @return 返回Response成功/失败信息
	*/
	@PostMapping("/setQuestionList")
	public Response<String> setQuestionList(@RequestBody QuestionList questionList,HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		questionList.setQuestionsAuthor(userId);
		int row=studentService.setQuestionList(questionList);
		if(row==0) {
			state="1";
			msg="提问失败";
		}else {
			state="200";
			msg="提问成功";
		}
		Response<String> response=new Response<String>(state, msg,null);
		return response;
	}
	
	
	/** * 学生用户请求问题详情
	* @param question 前端需要请求的问题
	* @return 返回Response，其中含有问题详情
	*/
	@PostMapping("/getQuestionDetails")
	public Response<QuestionsDetails> getQuestionDetails(@RequestBody QuestionList question) {
		Response<QuestionsDetails> response=new Response<QuestionsDetails>("200", "访问成功", studentService.getQuestionDetails(question.getQuestionsId()));
		return response;
	}
	
	/** * 学生用户回答问题
	* @param answer 回答问题的信息，作者信息，问题详情
	* @return 返回Response成功/失败信息
	*/
	@PostMapping("/insertQuestionDetails")
	public Response<String> insertQuestionDetails(@RequestBody Answer answer,HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		

		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		int row=studentService.insertQuestionDetails(userId, answer.getQuestionId(), answer.getAnswerData());
		if(row==0) {
			state="1";
			msg="回答失败";
		}else {
			state="200";
			msg="回答成功";
		}
		Response<String> response=new Response<String>(state, msg, null);
		return response;
	}
	
	
	/** * 学生用户线上测试
	* @param 
	* @return 返回线上测试
	*/
	@PostMapping("/getTestList")
	public Response<List<Test>> getTestList(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<Test>> response=new Response<List<Test>>("200", "访问成功", studentService.getTestList(userId));
		return response;
	}
	
	
	/** * 学生用户获取线上测试的题目
	* @param test线上测试的题目信息，用来找到测试详情
	* @return 返回线上测试中的答题列表
	*/
	@PostMapping("/getTestDetails")
	public Response<Test> getTestDetails(@RequestBody Test test,HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<Test> response=new Response<Test>("200", "访问成功", studentService.getTestDetails(userId, test.getTestId()));
		return response;
	}
	
	
	/** * 处理学生用户的线上测试答案，并写入成绩
	* @param testAnswer 
	* @return 返回提交情况
	*/
	@PostMapping("/setTestAnswer")
	public Response<String> setTestAnswer(@RequestBody TestAnswer testAnswer,HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		int row=studentService.setTestAnswer(testAnswer, userId);
		if(row==0) {
			state="1";
			msg="提交失败";
		}else {
			state="200";
			msg="提交成功";
		}
		Response<String> response=new Response<String>(state, msg,null );
		return response;
	}
	
	
	/** * 获取学生第一次面试信息
	* @param testAnswer 
	* @return 返回面试信息
	*/
	@PostMapping("/getOneInterview")
	public Response<List<Recruit>> getOneInterview(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", studentService.getOneInterview(userId));
		return response;
	}
	
	/** * 获取学生第二次面试信息
	* @param testAnswer 
	* @return 返回面试信息
	*/
	@PostMapping("/getTwoInterview")
	public Response<List<Recruit>> getTwoInterview(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", studentService.getTwoInterview(userId));
		
		return response;
	}
	
	
	
	/** * 获取学生面试结果
	* @param testAnswer 
	* @return 返回面试结果
	*/
	@PostMapping("/getResultInterview")
	public Response<List<Recruit>> getResultInterview(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
		String userId =JwtDecoder.ToDecodoer(tokenHeader);
		Response<List<Recruit>> response=new Response<List<Recruit>>("200", "访问成功", studentService.getResultInterview(userId));
		
		return response;
	}
}
