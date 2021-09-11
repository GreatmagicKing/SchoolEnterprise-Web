package com.school.entity;

public class Answer {
	String answerName;
    String answerData;
    String answerId;
    int questionId;
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getAnswerName() {
		return answerName;
	}
	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}
	public String getAnswerData() {
		return answerData;
	}
	public void setAnswerData(String answerData) {
		this.answerData = answerData;
	}
}
