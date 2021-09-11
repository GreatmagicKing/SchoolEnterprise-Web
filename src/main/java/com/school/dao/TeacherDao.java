package com.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
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
* @deion: 处理教师用户发来的请求Dao层
*/
@Mapper
public interface TeacherDao {
	
	/** * 教师获取自己教学的课程，并返回所要编辑的学生成绩列表
	* @param userId
	* @return List<TeacherScore>返回所要编辑课程成绩的信息
	*/
	List<TeacherScore> getTeacherScores(@Param("userId") String userId);
	
	/** * 教师编辑学生课程成绩
	* @param teacherScore 学生id和学生课程成绩
	* @return 返回response
	*/
	int setTeacherScores(@Param("teacherScore") TeacherScore teacherScore);
	
	/** * 教师获取问题列表
	* @param userId
	* @return List<QuestionList>返回问题列表
	*/
	List<QuestionList> getTeacherQuestion(@Param("userId") String userId);
	
	/** * 教师获取自己课程的线上测试
	* @param userId
	* @return List<Test>返回线上测试
	*/
	List<Test> getTeacherTest(@Param("userId") String userId);
	
	/** * 教师发布线上测试
	* @param testId
	* @return 返回response
	*/
	int releaseTest(@Param("testId") int testId);
	
	/** * 教师取消发布线上测试
	* @param testId
	* @return 返回response
	*/
	int revokeTest(@Param("testId") int testId);
	
	/** * 教师获取学生用户的线上测试成绩
	* @param userId
	* @return 返回学生线上测试成绩
	*/
	List<TeacherScore> showTestScore(@Param("userId") String userId);
	
	/** * 教师获取HR分配的学生面试信息
	* @param userId
	* @return 返回第二次面试信息
	*/
	List<Recruit> getTeacherInterview(@Param("userId") String userId);
	
	/** * 教师编辑第二次面试成绩
	* @param recruit 面试者信息
	* @return 返回response
	*/
	int setTeacherInterview(@Param("recruit")Recruit recruit);
	
	/** * 检查成绩表中是否有这名学生的信息
	* @param 
	* @return 
	*/
	List<TeacherScore> checkTeacherScores(@Param("teacherScore") TeacherScore teacherScore);
	
	/** * 插入成绩
	* @param 
	* @return 
	*/
	int insertTeacherScores(@Param("teacherScore") TeacherScore teacherScore);
	
	/** * 插入测试信息
	* @param 
	* @return 
	*/
	int setTestList(@Param("test") Test test);
	
	/** * 插入测试题目
	* @param 
	* @return 
	*/
	int setTestDetail(@Param("testId") int testId,@Param("problemId") int problemId);
	
	/** * 更新测试题目
	* @param 
	* @return 
	*/
	int updateTestDetail(@Param("testProblem") TestProblem testProblem);
	
	/** * 获取测试题目
	* @param 
	* @return 
	*/
	List<TestProblem> getTestProblem(@Param("test") Test test);
	
	/** * 获取测试题目
	* @param 
	* @return 
	*/
	List<TestProblem> getTeacherCourse(@Param("test") Test test);
	
	/** * 获取教师课程信息
	* @param 
	* @return 
	*/
	TeacherCourse getTeacherCourse(@Param("userId") String userId);
	
}
