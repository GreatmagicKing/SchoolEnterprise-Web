package com.school.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.school.entity.QuestionList;
import com.school.entity.QuestionsDetails;
import com.school.entity.Recruit;
import com.school.entity.StudentScore;
import com.school.entity.Test;
import com.school.entity.TestAnswer;

/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理学生用户发来的请求Service层
*/
public interface StudentService {

	/** * 学生用户获取学生的成绩
	* @param userId
	* @return List<StudentScore> 返回学生成绩
	*/
	List<StudentScore> getStudentScores(String userId);
	
	/** * 学生用户获取问题列表
	* @param 
	* @return List<QuestionList>  返回问题列表
	*/
	List<QuestionList> getQuestionList();
	
	/** * 学生用户写入问题
	* @param question 前端发回的问题体
	* @return 返回Response成功/失败信息
	*/
	int setQuestionList(QuestionList question);
	
	/** * 学生用户请求问题详情
	* @param questionId 前端需要请求的问题
	* @return QuestionsDetails，其中含有问题详情
	*/
	QuestionsDetails getQuestionDetails(int questionId);
	
	/** * 学生用户回答问题
	* @param answer 回答问题的信息，作者信息，问题详情
	* @return  int返回Response成功/失败信息
	*/
	int insertQuestionDetails(String answerId,int questionId,String answerData);
	
	/** * 学生用户线上测试
	* @param userId
	* @return List<Test>返回线上测试
	*/
	List<Test> getTestList(String userId);
	
	/** * 学生用户获取线上测试的题目
	* @param userId
	* @param testId
	* @return Test 返回线上测试中的答题列表
	*/
	Test getTestDetails(String userId,int testId);
	
	/** * 处理学生用户的线上测试答案，并写入成绩
	* @param testAnswer 
	* @param userId 
	* @return int返回提交情况
	*/
	int setTestAnswer(TestAnswer testAnswer,String userId);
	
	/** * 获取学生第一次面试信息
	* @param userId 
	* @return List<Recruit>返回面试信息
	*/
	List<Recruit> getOneInterview(String userId);
	
	/** * 获取学生第二次面试信息
	* @param userId 
	* @return List<Recruit>返回面试信息
	*/
	List<Recruit> getTwoInterview(String userId);
	
	/** * 获取学生面试结果
	* @param userId 
	* @return List<Recruit>返回面试结果
	*/
	List<Recruit> getResultInterview(String userId);
}
