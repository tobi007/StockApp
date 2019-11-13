package com.tobi.stock.repositories;

import com.tobi.stock.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 1:20 AM
 **/

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findBySymbol(String symbol);

    Stock findBySymbolAndPortfolioId(String symbol, Long portfolioId);

    List<Stock> findByPortfolioId(Long portfolioId);

    List<Stock> findByInHandAndPortfolioId(boolean inHand, Long portfolioId);

    List<Stock> findDistinctByInHandAndPortfolioId(boolean inHand, Long portfolioId);


    Long countByInHandAndPortfolioId(Boolean inHand, Long portfolioId);
}
