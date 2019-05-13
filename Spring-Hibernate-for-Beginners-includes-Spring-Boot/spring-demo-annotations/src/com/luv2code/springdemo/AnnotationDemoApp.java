package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach theCoach = context.getBean("thatSillyCoach", Coach.class);

        System.out.println(theCoach.getDailyWorkout());
        System.out.println(Coach.class);

        context.close();

    }
}
