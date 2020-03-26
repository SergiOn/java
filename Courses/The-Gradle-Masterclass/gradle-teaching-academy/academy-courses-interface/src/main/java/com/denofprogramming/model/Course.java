package com.denofprogramming.model;

public class Course extends DomainObject {

	
	private String name;
		
	
	public Course(){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}	
}
