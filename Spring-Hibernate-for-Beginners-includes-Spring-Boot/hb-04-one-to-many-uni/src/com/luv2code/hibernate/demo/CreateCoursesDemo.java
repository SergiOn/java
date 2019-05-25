package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(Instructor2.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            Instructor tempInstructor = session.get(Instructor.class, 1);
            Instructor2 tempInstructor2 = session.get(Instructor2.class, 1);

            System.out.println(tempInstructor);
            System.out.println(tempInstructor2);

            Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
            Course tempCourse2 = new Course("The Pinball Masterclass");

            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            session.save(tempCourse1);
            session.save(tempCourse2);

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
