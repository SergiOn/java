package com.denofprogramming.repository;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.denofprogramming.model.Registration;

@Component
public class RegistrationRepository implements RegistrationRepositoryInterface{

	
	private static final Logger logger = Logger.getLogger("com.denofprogramming.repository.RegistrationRepository");

	@Override
	public Registration read(Registration domain) {	
		logger.info("Entered method " + this.getClass().getSimpleName() + " read() with " + domain);
		final Registration registration = new Registration();
		registration.setId(domain.getId());
		registration.setRegisteredDate(new Date());
		logger.info("Exiting method " + this.getClass().getSimpleName() + " read() with " + registration);
		return registration;
	}

	@Override
	public Registration update(Registration domain) {
		return null;
	}
	
	

	

}
