package com.denofprogramming.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.denofprogramming.model.Registration;
import com.denofprogramming.repository.RegistrationRepositoryInterface;

@Service
public class RegistrationService implements RegistrationServiceInterface {

	@Inject
	private RegistrationRepositoryInterface registrationRepository;
	
	private static final Logger logger = Logger.getLogger("com.denofprogramming.service.RegistrationService");


	@Override
	public Registration read(Registration domain) {
		logger.info("Entered method " + this.getClass().getSimpleName() + " read() with " + domain);
				
		final Registration registration = registrationRepository.read(domain);
		
		logger.info("Exiting method " + this.getClass().getSimpleName() + " read() with " + registration);
		return registration;
	}


	@Override
	public Registration update(Registration domainObject) {		
		return null;
	}
	


	
}
