package com.denofprogramming.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.denofprogramming.model.Student;
import com.denofprogramming.model.User;

@Service
public class StudentService implements StudentServiceInterface {

	private static final Logger logger = Logger.getLogger("com.denofprogramming.service.StudentService");

	@Inject
	private UserServiceInterface userService;

	@Override
	public Student read(final Student domain) {		
		logger.info("Entered StudentService->read with:" + domain);
		final User user = userService.read(domain);
		final Student student = Student.valueOf(user);
		logger.info("Exiting StudentService->read with:" + student);
		return student;
	}

	@Override
	public Student update(Student domain) {
		return null;
	}

}
