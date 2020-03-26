package com.denofprogramming.repository;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.denofprogramming.model.Course;

@Component
public class CourseRepository implements CourseRepositoryInterface{

	
	private static final Logger logger = Logger.getLogger("com.denofprogramming.repository.CourseRepository");
	
	@Override
	public Course read(Course domain) {	
		logger.info("Entered method " + this.getClass().getSimpleName() + " read() with " + domain);
		
		final Course course = new Course();
		course.setId(Long.valueOf("10102"));
		course.setName("Spring101");
		
		logger.info("Exiting method " + this.getClass().getSimpleName() + " read() with " + course);	
		return course;
	}

	@Override
	public Course update(Course domain) {
		return null;
	}

	

}
