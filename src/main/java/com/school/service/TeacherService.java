package com.school.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.school.entity.InterviewR;
import com.school.entity.QuestionList;
import com.school.entity.Recruit;
import com.school.entity.TeacherCourse;
import com.school.entity.Test;
import com.school.entity.TestProblem;
import com.school.entity.teacher.TeacherScore;
/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理教师用户发来的请求Service层
*/
public interface TeacherService {

	/** * 教师获取自己教学的课程，并返回所要编辑的学生成绩列表
	* @param userId
	* @return List<TeacherScore>返回所要编辑课程成绩的信息
	*/
	List<TeacherScore> getTeacherScores(String userId);
	
	/** * 教师编辑学生课程成绩
	* @param teacherScore 学生id和学生课程成绩
	* @return 返回response
	*/
	int setTeacherScores(TeacherScore teacherScore);
	
	/** * 教师获取问题列表
	* @param userId
	* @return List<QuestionList>返回问题列表
	*/
	List<QuestionList> getTeacherQuestion(String userId);
	
	/** * 教师获取自己课程的线上测试
	* @param userId
	* @return List<Test>返回线上测试
	*/
	List<Test> getTeacherTest(String userId);
	
	/** * 教师发布线上测试
	* @param testId
	* @return 返回response
	*/
	int releaseTest(int testId);
	
	/** * 教师取消发布线上测试
	* @param testId
	* @return 返回response
	*/
	int revokeTest(int testId);
	
	/** * 教师获取学生用户的线上测试成绩
	* @param userId
	* @return 返回学生线上测试成绩
	*/
	List<TeacherScore> showTestScore(String userId);
	
	/** * 教师获取HR分配的学生面试信息
	* @param userId
	* @return 返回第二次面试信息
	*/
	List<Recruit> getTeacherInterview(String userId);
	
	/** * 教师编辑第二次面试成绩
	* @param recruit 面试者信息
	* @return 返回response
	*/
	int setTeacherInterview(Recruit recruit);
	
	/** * 插入测试信息
	* @param 
	* @return 返回response
	*/
	int setTestList(Test test,String userId);
	
	/** * 插入测试题目
	* @param 
	* @return 返回response
	*/
	int setTestDetail(TestProblem testProblem);
	
	/** * 获取测试题目
	* @param 
	* @return 
	*/
	List<TestProblem> getTestProblem(Test test);
	
	/** * 获取教师课程信息
	* @param 
	* @return 
	*/
	TeacherCourse getTeacherCourse(String userId);
}
