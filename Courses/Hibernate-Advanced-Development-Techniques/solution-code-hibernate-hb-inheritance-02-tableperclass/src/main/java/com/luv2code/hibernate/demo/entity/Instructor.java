package com.luv2code.hibernate.demo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor extends User {

    private double salary;

    public Instructor(String firstName, String lastName, String email, double salary) {
        super(firstName, lastName, email);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
