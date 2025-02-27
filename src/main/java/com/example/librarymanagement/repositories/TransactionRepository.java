package com.example.librarymanagement.repositories;

import com.example.librarymanagement.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.book.status = 'BORROWED' " +
            "AND t.returnDate IS NULL " +
            "AND t.borrowDate <= :overdueDate")
    List<Transaction> findOverdueTransactions(@Param("overdueDate") LocalDateTime overdueDate);
}
