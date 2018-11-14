package com.packt.petstore.controller;

import com.packt.petstore.exception.NotFoundInDBException;
import com.packt.petstore.exception.UserDoNotHaveEnoughFoundsException;
import com.packt.petstore.model.Pet;
import com.packt.petstore.model.PetStoreUser;
import com.packt.petstore.repository.PetRepository;
import com.packt.petstore.repository.PetStoreUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BuyController {

    private PetRepository petRepository;
    private PetStoreUserRepository petStoreUserRepository;

    public BuyController(PetRepository petRepository, PetStoreUserRepository petStoreUserRepository) {
        this.petRepository = petRepository;
        this.petStoreUserRepository = petStoreUserRepository;
    }

    @PostMapping("/buy/{petId}")
    public Mono<ResponseEntity<Pet>> buyPet(
            @PathVariable("petId") String petId,
            @RequestParam("userId") String userId) {

        if (userId == null || userId.isEmpty())
            return Mono.just(ResponseEntity.badRequest().build());

        Mono<PetStoreUser> userMono = petStoreUserRepository.findById(userId)
                .switchIfEmpty(Mono.error(new NotFoundInDBException("User not found in DB")));

        return this.petRepository.findById(petId)
                .switchIfEmpty(Mono.error(new NotFoundInDBException("Pet is not on the records")))
                .zipWith(userMono, (pet, user) -> {

                    if (user.getMoneyAvailable() < pet.getCost()) {
                        throw new UserDoNotHaveEnoughFoundsException(String.format("The User %s do not have enough founds!", userId));
                    }

                    user.setMoneyAvailable(user.getMoneyAvailable() - pet.getCost());
                    pet.setNumberInStock(pet.getNumberInStock() - 1);

                    return petStoreUserRepository.save(user)
                            .then(petRepository.save(pet))
                            .thenReturn(ResponseEntity.ok(pet));

                })
                .flatMap(t -> t);
    }
}
