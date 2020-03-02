package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;

            session.beginTransaction();

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Student before " + myStudent);

            myStudent.setFirstName("Scooby");

            System.out.println("Student after " + myStudent);

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("update Student set email = 'foo@gmail.com'")
                    .executeUpdate();

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
