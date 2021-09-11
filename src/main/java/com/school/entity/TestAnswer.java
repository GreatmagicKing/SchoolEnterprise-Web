package com.school.entity;

import java.util.Arrays;

public class TestAnswer {

	int testId;
    String testType;
    String testPart;
    String arrValue[];
    String arrValueString;
	public String getArrValueString() {
		return arrValueString;
	}
	public void setArrValueString(String arrValueString) {
		this.arrValueString = arrValueString;
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
	public String[] getArrValue() {
		return arrValue;
	}
	public void setArrValue(String[] arrValue) {
		this.arrValue = arrValue;
	}
    
}
