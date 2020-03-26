package com.denofprogramming.model;

import java.util.Date;

public class Registration extends DomainObject {

	
	private Student student;
	private Course course;
	private Date registeredDate;
	
	public Registration(){
		super();
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[student=" + student + ", course=" + course + "]";
	}
	
}
