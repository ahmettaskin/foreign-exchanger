package com.taskinah.exchanger.repository;

import com.taskinah.exchanger.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    Page<Transaction> getTransactionByCreatedAtAfterAndCreatedAtBefore(Pageable pageable, Date createdAtAfter, Date createdAtBefore);

    Page<Transaction> getTransactionByTransactionId(Pageable pageable, String transactionId);
}

