package com.userfront.dao;

import com.userfront.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();

}
