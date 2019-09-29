package com.tobi.stock.repositories;

import com.tobi.stock.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 6:19 PM
 **/

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByPortfolioId(Long portfolioId);
    List<Transaction> findByPortfolioIdAndCreatedOnBetween(Long portfolioId, Date from, Date to);
}
