package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class WrestlingCoach implements Coach {

	private FortuneService fortuneService;
			
	public WrestlingCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your double leg takedown.";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
