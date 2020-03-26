package com.denofprogramming.controller;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.denofprogramming.model.Student;
import com.denofprogramming.service.StudentServiceInterface;

@Controller
public class StudentController implements StudentControllerInterface {

	
	private static final Logger logger = Logger.getLogger("com.denofprogramming.controller.StudentController");
	
	@Inject
	private StudentServiceInterface studentService;

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView student() {
		
		return new ModelAndView("student", "command", new Student());
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb") final Student student, final ModelMap model) {
		
		logger.info("Entered StudentController->addStudent with:" + student);
		
		model.addAttribute("id", student.getId());
		System.out.println(student);
		final Student studentDetails = read(student);
				
		model.addAttribute("name", studentDetails.getName());
		model.addAttribute("age", studentDetails.getAge());
		
		logger.info("Exiting StudentController->addStudent with:" + studentDetails);
		return "result";
	}

	@Override
	public Student read(Student domain) {
		return studentService.read(domain);
	}

	@Override
	public Student update(Student domain) {
		return studentService.update(domain);
	}

}
