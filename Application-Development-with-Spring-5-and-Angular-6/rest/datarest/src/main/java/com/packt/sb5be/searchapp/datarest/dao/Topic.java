package com.packt.sb5be.searchapp.datarest.dao;
// Generated Nov 11, 2018 5:15:37 PM by Hibernate Tools 5.2.0.Final


import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Topic generated by hbm2java
 */
@Entity
@Table(name="topic"
    ,catalog="searchapp"
)
public class Topic implements java.io.Serializable {


     private Long id;
     private String name;
     private String description;
     private String textField1;
     private String textField2;
     private Date created;

    public Topic() {
    }

    public Topic(String name, String description, String textField1, String textField2, Date created) {
       this.name = name;
       this.description = description;
       this.textField1 = textField1;
       this.textField2 = textField2;
       this.created = created;
    }

     @Id @GeneratedValue(strategy=IDENTITY)


    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name="name", length=80)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name="description", length=5000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name="text_field1", length=5000)
    public String getTextField1() {
        return this.textField1;
    }

    public void setTextField1(String textField1) {
        this.textField1 = textField1;
    }


    @Column(name="text_field2", length=5000)
    public String getTextField2() {
        return this.textField2;
    }

    public void setTextField2(String textField2) {
        this.textField2 = textField2;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", length=26)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }




}


