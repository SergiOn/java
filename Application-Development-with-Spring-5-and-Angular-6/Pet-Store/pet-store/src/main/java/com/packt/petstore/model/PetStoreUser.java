package com.packt.petstore.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "petStoreUser")
public class PetStoreUser {

    private @Id String id;
    private String name;
    private Long moneyAvailable;
    private String email;
    private String password;

}
