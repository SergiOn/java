package com.denofprogramming.model;

public class Student extends User {

	public Student() {
		super();
	}

	public static Student valueOf(final User user) {
		final Student student = new Student();
		student.setId(user.getId());
		student.setName(user.getName());
		student.setAge(user.getAge());
		return student;
	}

}
