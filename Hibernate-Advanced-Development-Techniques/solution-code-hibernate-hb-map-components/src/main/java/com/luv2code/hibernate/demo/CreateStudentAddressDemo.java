package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Address;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentAddressDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .addAnnotatedClass(Address.class)
                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create the object
            Student tempStudent = new Student("John", "Doe", "john@luv2code.com");

            // create the address object
            Address homeAddress = new Address("Some Street", "Some City", "12345");
            Address billingAddress = new Address("Some Billing Street", "Some Billing City", "101010");

            // start the transaction
            session.beginTransaction();

            // save the object
            System.out.println("Saving the student and address...");
//            tempStudent.setHomeAddress(homeAddress);
            tempStudent.setBillingAddress(billingAddress);
            session.persist(tempStudent);

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
