package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Student> theStudents = session.createQuery("from Student").getResultList();

            for (Student student : theStudents) {
                System.out.println("Student: " + student);
            }

            theStudents = session.createQuery("from Student s where s.firstName = 'Daffy'").getResultList();

            for (Student student : theStudents) {
                System.out.println("Student, Doe: " + student);
            }

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
