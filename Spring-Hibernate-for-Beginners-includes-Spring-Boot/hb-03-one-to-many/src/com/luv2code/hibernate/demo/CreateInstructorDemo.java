package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@mail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com", "Video Games");

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();
            session.save(tempInstructor);
            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
