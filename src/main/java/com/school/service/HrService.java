package com.school.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.school.entity.AcceptanceRate;
import com.school.entity.InterviewR;
import com.school.entity.QuestionList;
import com.school.entity.Recruit;
import com.school.entity.StudentRate;
import com.school.entity.StudentScore;
import com.school.entity.User;
/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理HR用户发来的请求HrService层
*/
public interface HrService {

	/** * 获取学生成绩，和评级
	* @param 
	* @return response
	*/
	List<StudentRate> getStudentRate();
	
	/** * 获取学生提出的问题
	* @param userId 学生的ID
	* @return 返回问题列表
	*/
	List<QuestionList> getHrQuestionList(String userId);
	
	/** * 获取学生回答的问题
	* @param userId 学生Id
	* @return 返回问题列表
	*/
	List<QuestionList> getHrAnswerList(String userId);
	
	/** * 获取HR第一次面试的学生成绩
	* @param recruit面试信息
	* @return Hr需要的面试信息
	*/
	int setMyInterview(@Param("recruit")Recruit recruit);
	
	/** * 获取HR第一次面试的学生信息
	* @param userId 学生Id
	* @return 第一次面试的学生信息
	*/
	List<Recruit> getMyInterview(@Param("userId") String userId);
	
	/** * 获取每一个需要面试的学生
	* @param 
	* @return 每一个需要面试的学生
	*/
	List<Recruit> getEveryRecruit();
	
	/** * 获取通过第一次面试，进入第二次面试的学生
	* @param 
	* @return 第二次面试学生
	*/
	List<Recruit> getTwoRecruit();
	
	/** * 获取第一次面试学生的结果
	* @param 
	* @return 面试结果，包含各项学生信息
	*/
	List<Recruit> getHrRecruit();
	
	/** * 分配第一次面试的人员
	* @param recruit面试人员信息
	* @return 
	*/
	int setHrRecruit(Recruit recruit);
	
	/** * 获取教师二面试面试结果
	* @param 
	* @return 
	*/
	List<Recruit> getTeacherRecruit();
	
	/** * 分配教师二面面试
	* @param recruit面试人员信息
	* @return 
	*/
	int setTeacherRecruit(Recruit recruit);
	
	/** * 获取面试最终信息
	* @param 
	* @return 面试最终信息
	*/
	List<InterviewR> getInterviewR();
	
	/** * 编辑面试结果
	* @param interviewR 面试结果信息
	* @return 
	*/
	int setInterviewR(InterviewR interviewR);
	
	/** * 返回用户列表
	* @param 
	* @return 
	*/
	List<User> getUserList();
	
	/** * 插入用户
	* @param 
	* @return 
	*/
	int setUserList(User user);
	
	/** * 获取成绩平均值
	* @param 
	* @return 
	*/
	List<StudentScore> getAverageScore();
	
	/** * 获取录取率
	* @param 
	* @return 
	*/
	List<AcceptanceRate> getAcceptanceRate();
}
