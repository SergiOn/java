package com.luv2code.hibernate.demo.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ElementCollection
    @CollectionTable(name = "image")
//    @OrderBy // only one possible order; file_name asc
    @org.hibernate.annotations.OrderBy(clause = "file_name desc") // default asc
//    @OrderBy(value = "file_name desc") // default asc
    @Column(name = "file_name")
    private Set<String> images = new LinkedHashSet<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id + ", " +
                "firstName='" + firstName + '\'' + ", " +
                "lastName='" + lastName + '\'' + ", " +
                "email='" + email + '\'' +
                '}';
    }
}
