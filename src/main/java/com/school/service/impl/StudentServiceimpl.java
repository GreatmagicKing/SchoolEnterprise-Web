package com.school.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dao.StudentDao;
import com.school.entity.QuestionList;
import com.school.entity.QuestionsDetails;
import com.school.entity.Recruit;
import com.school.entity.StudentScore;
import com.school.entity.Test;
import com.school.entity.TestAnswer;
import com.school.entity.TestResult;
import com.school.service.StudentService;
/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理学生用户发来的请求Service层
*/
@Service
public class StudentServiceimpl implements StudentService{

	@Autowired
	StudentDao sDao;
	
	
	/** * 学生用户获取学生的成绩
	* @param userId
	* @return List<StudentScore> 返回学生成绩
	*/
	@Override
	public List<StudentScore> getStudentScores(String userId) {
		// TODO Auto-generated method stub
		
		return sDao.getStudentScores(userId);
	}

	/** * 学生用户获取问题列表
	* @param 
	* @return List<QuestionList>  返回问题列表
	*/
	@Override
	public List<QuestionList> getQuestionList() {
		// TODO Auto-generated method stub
		return sDao.getQuestionList();
	}
	
	/** * 学生用户写入问题
	* @param question 前端发回的问题体
	* @return 返回Response成功/失败信息
	*/
	@Override
	public int setQuestionList(QuestionList question) {
		// TODO Auto-generated method stub
		return sDao.setQuestionList(question);
	}

	/** * 学生用户请求问题详情
	* @param questionId 前端需要请求的问题
	* @return QuestionsDetails，其中含有问题详情
	*/
	@Override
	public QuestionsDetails getQuestionDetails(int questionId) {
		// TODO Auto-generated method stub
		QuestionsDetails questionsDetails=sDao.getQuestionDetails(questionId);
		questionsDetails.setAnswer(sDao.getAnswer(questionId));
		return questionsDetails;
	}
	
	/** * 学生用户回答问题
	* @param answer 回答问题的信息，作者信息，问题详情
	* @return  int返回Response成功/失败信息
	*/
	@Override
	public int insertQuestionDetails(String answerId,int questionId,String answerData) {
		// TODO Auto-generated method stub
		return sDao.insertQuestionDetails(answerId,questionId,answerData);
	}

	/** * 学生用户线上测试
	* @param userId
	* @return List<Test>返回线上测试
	*/
	@Override
	public List<Test> getTestList(String userId) {
		// TODO Auto-generated method stub
		return sDao.getTestList(userId);
	}

	/** * 学生用户获取线上测试的题目
	* @param userId
	* @param testId
	* @return Test 返回线上测试中的答题列表
	*/
	@Override
	public Test getTestDetails(String userId, int testId) {
		// TODO Auto-generated method stub
		
		return sDao.getTestDetails(userId, testId);
	}

	/** * 处理学生用户的线上测试答案，并写入成绩
	* @param testAnswer 
	* @param userId 
	* @return int返回提交情况
	*/
	@Override
	public int setTestAnswer(TestAnswer testAnswer,String userId) {
		// TODO Auto-generated method stub
		testAnswer.setArrValueString(Arrays.toString(testAnswer.getArrValue()));
		List<TestResult> testResult=sDao.getTestResult(testAnswer.getTestId());
		int score=0;
		for(int i=0;i<testResult.size();i++) {
			if(testResult.get(i).getTestResult().equals(testAnswer.getArrValue()[i])) {
				score=score+1;
			}
		}
		int num=0;
//		System.out.println();
		if(sDao.getStudentScore(testAnswer, userId).size()==0) {
			num+=sDao.insertTestScore(testAnswer, userId, score);
		}
		if (sDao.getTeststudent(testAnswer, userId).size()==0) {
			num+=sDao.insertTeststudent(testAnswer, userId);
		}
		num+=sDao.setTestScore(testAnswer, userId, score);
		return num;
	}

	/** * 获取学生第一次面试信息
	* @param userId 
	* @return List<Recruit>返回面试信息
	*/
	@Override
	public List<Recruit> getOneInterview(String userId) {
		// TODO Auto-generated method stub
		return sDao.getOneInterview(userId);
	}

	/** * 获取学生第二次面试信息
	* @param userId 
	* @return List<Recruit>返回面试信息
	*/
	@Override
	public List<Recruit> getTwoInterview(String userId) {
		// TODO Auto-generated method stub
		return sDao.getTwoInterview(userId);
	}

	/** * 获取学生面试结果
	* @param userId 
	* @return List<Recruit>返回面试结果
	*/
	@Override
	public List<Recruit> getResultInterview(String userId) {
		// TODO Auto-generated method stub
		return sDao.getResultInterview(userId);
	}

}
