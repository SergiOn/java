package com.denofprogramming.controller;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import com.denofprogramming.model.Course;
import com.denofprogramming.service.CourseServiceInterface;

@Controller
public class CourseController implements CourseControllerInterface{

	@Inject
	private CourseServiceInterface courseService;
	
	private static final Logger logger = Logger.getLogger("com.denofprogramming.controller.CourseController");
	
	@Override
	public Course read(Course domain) {
		logger.info("Entering method " + this.getClass().getSimpleName() + " read() with " + domain);
		final Course newDomain = courseService.read(domain);
		logger.info("Exiting method " + this.getClass().getSimpleName() + " read() with " + newDomain);
		return newDomain;
	}

	@Override
	public Course update(Course domain) {
		return courseService.update(domain);
	}

}
