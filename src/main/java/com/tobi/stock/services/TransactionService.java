package com.tobi.stock.services;

import com.tobi.stock.models.Transaction;
import com.tobi.stock.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 6:25 PM
 **/

@Service
public class TransactionService extends BaseService<Transaction, Long> {

    private TransactionRepository transactionRepository;


    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        setRepository(transactionRepository);
    }


    public List<Transaction> findByPortfolioId(Long portfolioId) {
        return transactionRepository.findByPortfolioId(portfolioId);
    }

    public List<Transaction> findByPortfolioIdAndCreatedOnBetween(Long portfolioId, Date from, Date to) {
        if (from == null || to == null)
            return transactionRepository.findByPortfolioId(portfolioId);
        return transactionRepository.findByPortfolioIdAndCreatedOnBetween(portfolioId, from, to);
    }
}
