package com.springliquibase.greetingservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class LanguageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String description;

}
