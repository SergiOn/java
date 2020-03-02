package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Status;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the object
            Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com", Status.ACTIVE);
            Student tempStudent2 = new Student("Marry", "Public", "mary@luv2code.com", Status.INACTIVE);

            // start the transaction
            session.beginTransaction();

            // save the object
            System.out.println("Saving the student...");
            session.persist(tempStudent1);
            session.persist(tempStudent2);

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
