package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
