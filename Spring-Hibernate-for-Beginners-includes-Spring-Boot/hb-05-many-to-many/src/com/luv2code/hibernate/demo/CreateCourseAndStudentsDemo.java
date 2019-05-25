package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

//            Course tempCourse = new Course("Pacman");
//            Course tempCourse = new Course("Java, Spring");
            Course tempCourse = new Course("Java, Spring, Hibernate");

            System.out.println("Saving the Course: " + tempCourse);

            session.save(tempCourse);

            Student tempStudent1 = new Student("John", "Doe", "john.doe@mail.com");
            Student tempStudent2 = new Student("Marry", "Doe", "john.doe@mail.com");

            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            session.save(tempStudent1);
            session.save(tempStudent2);

            System.out.println("Saved Students: " + tempCourse.getStudents());

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
