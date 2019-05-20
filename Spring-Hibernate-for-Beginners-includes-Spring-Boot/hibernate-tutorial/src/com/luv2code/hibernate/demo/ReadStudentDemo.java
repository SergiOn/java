package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Student tempStudent = new Student("Daffy", "Wall", "paul@mail.com");

            session.beginTransaction();

            System.out.println(tempStudent);

            session.save(tempStudent);
            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Id: " + tempStudent.getId());

            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Student " + myStudent);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
