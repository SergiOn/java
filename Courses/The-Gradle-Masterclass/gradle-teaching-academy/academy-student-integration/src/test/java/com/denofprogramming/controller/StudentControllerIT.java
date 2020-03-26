package com.denofprogramming.controller;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.denofprogramming.config.StudentTestConfig;
import com.denofprogramming.model.Student;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=StudentTestConfig.class)
public class StudentControllerIT {
	
	@Inject
	private StudentControllerInterface studentController;
	
	
	@Test
	public void readStudentTest(){		
		final Student domain = new Student();
		final Student returnStudent = studentController.read(domain);
		assertNotNull("returnStudent should not be null", returnStudent);
		Assert.assertTrue("student name is not correct", returnStudent.getName().equals("Jim Beam"));
		Assert.assertTrue("student age is not correct", returnStudent.getAge() == 46);
	}
	
}
