package com.school.entity;

import java.util.List;

public class QuestionsDetails {

	String questionsId;
	String questionsTitle;
	String questionsData;
	String questionsAuthor;
	String questionsTime;
	String questionsType;
	String authorName;
	String Html;
	String Js;
	String Java;
	String Ts;
	String Angular;
	String MyBatis;
	String SQL;
	String Junit;
	List<Answer> answer;
	public String getQuestionsId() {
		return questionsId;
	}
	public void setQuestionsId(String questionsId) {
		this.questionsId = questionsId;
	}
	public String getQuestionsTitle() {
		return questionsTitle;
	}
	public void setQuestionsTitle(String questionsTitle) {
		this.questionsTitle = questionsTitle;
	}
	public String getQuestionsData() {
		return questionsData;
	}
	public void setQuestionsData(String questionsData) {
		this.questionsData = questionsData;
	}
	public String getQuestionsAuthor() {
		return questionsAuthor;
	}
	public void setQuestionsAuthor(String questionsAuthor) {
		this.questionsAuthor = questionsAuthor;
	}
	public String getQuestionsTime() {
		return questionsTime;
	}
	public void setQuestionsTime(String questionsTime) {
		this.questionsTime = questionsTime;
	}
	public String getQuestionsType() {
		return questionsType;
	}
	public void setQuestionsType(String questionsType) {
		this.questionsType = questionsType;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getHtml() {
		return Html;
	}
	public void setHtml(String html) {
		Html = html;
	}
	public String getJs() {
		return Js;
	}
	public void setJs(String js) {
		Js = js;
	}
	public String getJava() {
		return Java;
	}
	public void setJava(String java) {
		Java = java;
	}
	public String getTs() {
		return Ts;
	}
	public void setTs(String ts) {
		Ts = ts;
	}
	public String getAngular() {
		return Angular;
	}
	public void setAngular(String angular) {
		Angular = angular;
	}
	public String getMyBatis() {
		return MyBatis;
	}
	public void setMyBatis(String myBatis) {
		MyBatis = myBatis;
	}
	public String getSQL() {
		return SQL;
	}
	public void setSQL(String sQL) {
		SQL = sQL;
	}
	public String getJunit() {
		return Junit;
	}
	public void setJunit(String junit) {
		Junit = junit;
	}
	public List<Answer> getAnswer() {
		return answer;
	}
	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}
 
}
