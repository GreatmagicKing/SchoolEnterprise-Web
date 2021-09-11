package com.school.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.dao.TeacherDao;
import com.school.entity.InterviewR;
import com.school.entity.QuestionList;
import com.school.entity.Recruit;
import com.school.entity.TeacherCourse;
import com.school.entity.Test;
import com.school.entity.TestProblem;
import com.school.entity.teacher.TeacherScore;
import com.school.service.TeacherService;

@Service
public class TeacherServiceimpl implements TeacherService{

	@Autowired
	TeacherDao tDao;
	@Override
	public List<TeacherScore> getTeacherScores(String userId) {
		// TODO Auto-generated method stub
		return tDao.getTeacherScores(userId);
	}
	@Override
	public int setTeacherScores(TeacherScore teacherScore) {
		// TODO Auto-generated method stub
		
		System.out.println(teacherScore.getUserId());
		if(tDao.checkTeacherScores(teacherScore).size()==0) {
			return tDao.insertTeacherScores(teacherScore);
		}else {
			return tDao.setTeacherScores(teacherScore);
		}
	}
	@Override
	public List<QuestionList> getTeacherQuestion(String userId) {
		// TODO Auto-generated method stub
		return tDao.getTeacherQuestion(userId);
	}
	@Override
	public List<Test> getTeacherTest(String userId) {
		// TODO Auto-generated method stub
		return tDao.getTeacherTest(userId);
	}
	@Override
	public int releaseTest(int testId) {
		// TODO Auto-generated method stub
		return tDao.releaseTest(testId);
	}
	@Override
	public int revokeTest(int testId) {
		// TODO Auto-generated method stub
		return tDao.revokeTest(testId);
	}
	@Override
	public List<TeacherScore> showTestScore(String userId) {
		// TODO Auto-generated method stub
		return tDao.showTestScore(userId);
	}
	@Override
	public List<Recruit> getTeacherInterview(String userId) {
		// TODO Auto-generated method stub
		return tDao.getTeacherInterview(userId);
	}
	@Override
	public int setTeacherInterview(Recruit recruit) {
		// TODO Auto-generated method stub
		return tDao.setTeacherInterview(recruit);
	}
	@Override
	public int setTestList(Test test,String userId) {
		// TODO Auto-generated method stub
		List<Test> testList=tDao.getTeacherTest(userId);
		int testId=0;
		if (testList.size()<4) {
			tDao.setTestList(test);
			testId=test.getTestId();
			for(int i=1;i<=10;i++) {
				tDao.setTestDetail(testId, i);
			}
		}
		return testId;
	}
	@Override
	public int setTestDetail(TestProblem testProblem) {
		// TODO Auto-generated method stub
		return tDao.updateTestDetail(testProblem);
	}
	@Override
	public List<TestProblem> getTestProblem(Test test) {
		// TODO Auto-generated method stub
		return tDao.getTestProblem(test);
	}
	@Override
	public TeacherCourse getTeacherCourse(String userId) {
		// TODO Auto-generated method stub
		return tDao.getTeacherCourse(userId);
	}
	

}
