package com.packt.petstore.controller;

import com.packt.petstore.model.PetStoreUserDTO;
import com.packt.petstore.service.IReactivePetStoreUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

    private final IReactivePetStoreUserService petStoreUserService;

    public AccountController(IReactivePetStoreUserService petStoreUserService) {
        this.petStoreUserService = petStoreUserService;
    }

    @PostMapping("/register")
    public Mono<Void> registerUser(@RequestBody PetStoreUserDTO dto) {
        return this.petStoreUserService.registerNewUser(dto);
    }

}
