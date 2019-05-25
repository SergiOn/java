package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Instructor tempInstructor = new Instructor("Paul", "Wall", "paul@mail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/chanel", "My Channel");

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            session.beginTransaction();
            session.save(tempInstructor);
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
