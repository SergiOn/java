package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .addAnnotatedClass(Review.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            Course tempCourse = new Course("Pacman");

            Review review1 = new Review("Great course");
            Review review2 = new Review("Great course, job well done");
            Review review3 = new Review("What a dumb course");

            tempCourse.arrReview(review1);
            tempCourse.arrReview(review2);
            tempCourse.arrReview(review3);

            System.out.println("Course: " + tempCourse);
            System.out.println("Reviews: " + tempCourse.getReviews());

            session.save(tempCourse);

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
