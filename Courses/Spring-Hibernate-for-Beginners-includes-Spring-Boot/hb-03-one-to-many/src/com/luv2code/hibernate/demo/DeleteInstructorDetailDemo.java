package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, 8);

            System.out.println("tempInstructor: " + tempInstructorDetail);
            System.out.println("tempInstructor, instructor: " + tempInstructorDetail.getInstructor());

            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);

            session.getTransaction().commit();

        } catch (Exception exc){
            exc.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}