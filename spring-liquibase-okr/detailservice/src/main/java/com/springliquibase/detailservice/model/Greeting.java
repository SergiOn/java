package com.springliquibase.detailservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {

    private Long id;

    private String language;

    private String translation;

    private String description;

    private LanguageType languageType;

}
