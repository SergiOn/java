package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateUserStudentInstructorDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .addAnnotatedClass(Instructor.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the object
            Student tempStudent = new Student("John", "Doe", "john@luv2code.com", "Hibernate");
            Instructor tempInstructor = new Instructor("Marry", "Public", "mary@luv2code.com", 20000.00);

            // start the transaction
            session.beginTransaction();

            // save the object
            System.out.println("Saving the student and instructor...");
            session.persist(tempStudent);
            session.persist(tempInstructor);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!!");

        } finally {
            // clean up code
            session.close();
            factory.close();
        }

    }

}
