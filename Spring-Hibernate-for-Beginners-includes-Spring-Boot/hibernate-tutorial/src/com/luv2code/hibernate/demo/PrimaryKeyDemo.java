package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Student tempStudent1 = new Student("John-1", "Wall", "paul@mail.com");
            Student tempStudent2 = new Student("Mary-2", "Wall", "paul@mail.com");
            Student tempStudent3 = new Student("Bonita-3", "Wall", "paul@mail.com");

            session.beginTransaction();
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
