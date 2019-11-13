package com.tobi.stock.repositories;

import com.tobi.stock.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 27/09/2019
 * Time: 2:31 PM
 **/

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Portfolio findByName(String name);

    Portfolio findByOwner(String owner);
}
