package com.denofprogramming.controller;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import com.denofprogramming.model.Course;
import com.denofprogramming.model.Registration;
import com.denofprogramming.model.Student;
import com.denofprogramming.service.RegistrationServiceInterface;

@Controller
public class RegistrationController implements RegistrationControllerInterface{

	@Inject
	private StudentControllerInterface studentController;

	@Inject
	private CourseControllerInterface courseController;
	
	@Inject
	private RegistrationServiceInterface registrationService;
	
	private static final Logger logger = Logger.getLogger("com.denofprogramming.controller.RegistrationController");
	
	@Override
	public Registration read(Registration domain) {
		
		logger.info("Entered method " + this.getClass().getSimpleName() + " read() with " + domain);
		final Course course = courseController.read(domain.getCourse());
		final Student student = studentController.read(domain.getStudent());
		
		final Registration registration = registrationService.read(domain);
		registration.setCourse(course);
		registration.setStudent(student);
		
		logger.info("Exiting method " + this.getClass().getSimpleName() + " read() with " + registration);
		return registration;
	}

	@Override
	public Registration update(Registration DomainObject) {
		return null;
	}



}
