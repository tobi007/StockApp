package com.tobi.stock.repositories;

import com.tobi.stock.models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 1:46 PM
 **/

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {
    Investor findByEmail(String email);

    Investor findByUserName(String userName);
}