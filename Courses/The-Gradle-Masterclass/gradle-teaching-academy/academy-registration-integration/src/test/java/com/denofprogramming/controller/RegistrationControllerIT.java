package com.denofprogramming.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.denofprogramming.config.RegistrationTestConfig;
import com.denofprogramming.model.Course;
import com.denofprogramming.model.Registration;
import com.denofprogramming.model.Student;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RegistrationTestConfig.class)
public class RegistrationControllerIT {
	
	@Inject
	private RegistrationControllerInterface registrationController;
	
	
	@Test
	public void readRegistrationIT(){
		final Course course = new Course();
		course.setId(Long.valueOf("101"));
		final Student student = new Student();
		student.setId(Long.valueOf("999"));
		final Registration registration = new Registration();
		registration.setCourse(course);
		registration.setStudent(student);
		
		final Registration returnRegistration = registrationController.read(registration);
		assertNotNull("returnRegistration should not be null", returnRegistration);
		assertTrue("Registration student name is not correct", returnRegistration.getStudent().getName().equals("Jim Sparrow"));
		assertTrue("Registration course name is not correct", returnRegistration.getCourse().getName().equals("Spring101"));
		assertNotNull("return Registration Date should not be null", returnRegistration.getRegisteredDate());
	}
	
}
