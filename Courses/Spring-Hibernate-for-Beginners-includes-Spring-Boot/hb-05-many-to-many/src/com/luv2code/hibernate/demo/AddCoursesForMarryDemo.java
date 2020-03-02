package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMarryDemo {

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

            Student tempStudent = session.get(Student.class, 2);

            System.out.println("Loaded Student: " + tempStudent);
            System.out.println("Courses:  " + tempStudent.getCourses());

            Course tempCourse1 = new Course("Rubik's cube");
            Course tempCourse2 = new Course("Go");

            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            session.save(tempCourse1);
            session.save(tempCourse2);

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
