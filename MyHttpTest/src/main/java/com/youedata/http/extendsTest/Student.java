package com.youedata.http.extendsTest;

public class Student extends Person {

	private String sno;
	
	private String classes;
	@Override
	public String say(String content){
		return "学生说："+content;
	}
	public String sing(String content){
		return "学生唱："+content;
	}
	public static void main(String[] args) {
		Person p=new Student();
		System.out.println(p.say("您好"));
	}
}
