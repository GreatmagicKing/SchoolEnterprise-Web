package com.school.entity;

import java.util.List;

public class Test {

	int testId;
	String testType;
	String testPart;
	String testName;
	String state; 
	String arrValueString;
	public String getArrValueString() {
		return arrValueString;
	}
	public void setArrValueString(String arrValueString) {
		this.arrValueString = arrValueString;
	}
	List<TestProblem> testProblem;
	public List<TestProblem> getTestProblem() {
		return testProblem;
	}
	public void setTestProblem(List<TestProblem> testProblem) {
		this.testProblem = testProblem;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestPart() {
		return testPart;
	}
	public void setTestPart(String testPart) {
		this.testPart = testPart;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
}
