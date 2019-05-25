package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .addAnnotatedClass(Review.class)
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);

            System.out.println("Loaded Student: " + tempStudent);
            System.out.println("Courses:  " + tempStudent.getCourses());

            session.delete(tempStudent);

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
