package com.school.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dao.HrDao;
import com.school.entity.AcceptanceRate;
import com.school.entity.InterviewR;
import com.school.entity.QuestionList;
import com.school.entity.Recruit;
import com.school.entity.StudentRate;
import com.school.entity.StudentScore;
import com.school.entity.User;
import com.school.service.HrService;


/*** @author: zhangjiajun
* @date: ----
* @version: 1.3.0
* @deion: 处理HR用户发来的请求Service层
*/
@Service
public class HrServiceimpl implements HrService{

	@Autowired
	HrDao hrDao;
	
	/** * 获取学生成绩，和评级
	* @param 
	* @return response
	*/
	@Override
	public List<StudentRate> getStudentRate() {
		// TODO Auto-generated method stub
		List<StudentRate> studentRates=hrDao.getStudentRate();
		
		for(int i=0;i<studentRates.size();i++) {
			double score=0;
//			double HomeWorkOne=0,HomeWorkTwo=0,HomeWorkThree=0,HomeWorkBig=0,
//					TestOne=0,TestTwo=0,TestThree=0,TestBig=0;
			score+=Double.parseDouble(studentRates.get(i).getHomeWorkOne());
			score+=Double.parseDouble(studentRates.get(i).getHomeWorkTwo());
			score+=Double.parseDouble(studentRates.get(i).getHomeWorkThree());
			score+=Double.parseDouble(studentRates.get(i).getHomeWorkBig());
			score+=Double.parseDouble(studentRates.get(i).getTestOne());
			score+=Double.parseDouble(studentRates.get(i).getTestTwo());
			score+=Double.parseDouble(studentRates.get(i).getTestThree());
			score+=Double.parseDouble(studentRates.get(i).getTestBig());
			
			double rate=0;
			rate=score/3520;
			if(rate>0.8) {
				studentRates.get(i).setRate("A");
			}else if (rate>0.7) {
				studentRates.get(i).setRate("B");
			}else if (rate>0.6) {
				studentRates.get(i).setRate("C");
			}else if (rate>0.5) {
				studentRates.get(i).setRate("D");
			}else {
				studentRates.get(i).setRate("暂无评级");
			}
		}
		return studentRates;
	}

	/** * 获取学生提出的问题
	* @param userId 学生的ID
	* @return 返回问题列表
	*/
	@Override
	public List<QuestionList> getHrQuestionList(String userId) {
		// TODO Auto-generated method stub
		return hrDao.getHrQuestionList(userId);
	}

	/** * 获取学生回答的问题
	* @param userId 学生Id
	* @return 返回问题列表
	*/
	@Override
	public List<QuestionList> getHrAnswerList(String userId) {
		// TODO Auto-generated method stub
		return hrDao.getHrAnswerList(userId);
	}


	/** * 获取每一个需要面试的学生
	* @param 
	* @return 每一个需要面试的学生
	*/
	@Override
	public List<Recruit> getEveryRecruit() {
		// TODO Auto-generated method stub
		return hrDao.getEveryRecruit();
	}

	/** * 获取第一次面试学生的结果
	* @param 
	* @return 面试结果，包含各项学生信息
	*/
	@Override
	public List<Recruit> getHrRecruit() {
		// TODO Auto-generated method stub
		return hrDao.getHrRecruit();
	}

	/** * 分配第一次面试的人员
	* @param recruit面试人员信息
	* @return 
	*/
	@Override
	public int setHrRecruit(Recruit recruit) {
		// TODO Auto-generated method stub
		return hrDao.setHrRecruit(recruit);
	}

	/** * 获取教师二面试面试结果
	* @param 
	* @return 
	*/
	@Override
	public List<Recruit> getTeacherRecruit() {
		// TODO Auto-generated method stub
		return hrDao.getTeacherRecruit();
	}

	/** * 分配教师二面面试
	* @param recruit面试人员信息
	* @return 
	*/
	@Override
	public int setTeacherRecruit(Recruit recruit) {
		// TODO Auto-generated method stub
		return hrDao.setTeacherRecruit(recruit);
	}


	@Override
	public List<Recruit> getTwoRecruit() {
		// TODO Auto-generated method stub
		return hrDao.getTwoRecruit();
	}

	/** * 获取面试最终信息
	* @param 
	* @return 面试最终信息
	*/
	@Override
	public List<InterviewR> getInterviewR() {
		// TODO Auto-generated method stub
		return hrDao.getInterviewR();
	}

	/** * 编辑面试结果
	* @param interviewR 面试结果信息
	* @return 
	*/
	@Override
	public int setInterviewR(InterviewR interviewR) {
		// TODO Auto-generated method stub
		return hrDao.setInterviewR(interviewR);
	}

	/** * 获取HR第一次面试的学生成绩
	* @param recruit面试信息
	* @return Hr需要的面试信息
	*/
	@Override
	public int setMyInterview(Recruit recruit) {
		// TODO Auto-generated method stub
		return hrDao.setMyInterview(recruit);
	}

	/** * 获取HR第一次面试的学生信息
	* @param userId 学生Id
	* @return 第一次面试的学生信息
	*/
	@Override
	public List<Recruit> getMyInterview(String userId) {
		// TODO Auto-generated method stub
		return hrDao.getMyInterview(userId);
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return hrDao.getUserList();
	}

	@Override
	public int setUserList(User user) {
		// TODO Auto-generated method stub
		return hrDao.setUserList(user);
	}

	@Override
	public List<StudentScore> getAverageScore() {
		// TODO Auto-generated method stub
		return hrDao.getAverageScore();
	}

	@Override
	public List<AcceptanceRate> getAcceptanceRate() {
		// TODO Auto-generated method stub
		return hrDao.getAcceptanceRate();
	}

	
}
