package jp.tashino.aws_utsulog.model;

import java.io.Serializable;

public class UtsulogModel implements Serializable{

	private String name;
	private String age;
	private String[] bigQuestion;
	private String[] smallQuestion;
	private boolean agreement;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String[] getBigQuestion() {
		return bigQuestion;
	}
	public void setBigQuestion(String[] bigQuestion) {
		this.bigQuestion = bigQuestion;
	}
	public String[] getSmallQuestion() {
		return smallQuestion;
	}
	public void setSmallQuestion(String[] smallQuestion) {
		this.smallQuestion = smallQuestion;
	}
	public boolean isAgreement() {
		return agreement;
	}
	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}

}
