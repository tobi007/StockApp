package com.tobi.stock.controllers;

import com.tobi.stock.configs.JwtTokenProvider;
import com.tobi.stock.controllers.api.LoginRequest;
import com.tobi.stock.models.Investor;
import com.tobi.stock.services.InvestorService;
import com.tobi.stock.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 2:23 PM
 **/

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;
    InvestorService investorService;
    PortfolioService portfolioService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setInvestorService(InvestorService investorService) {
        this.investorService = investorService;
    }

    @Autowired
    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            String username = loginRequest.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.investorService.findInvestorByUserName(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody Investor investor) {
        Investor userExists = investorService.findInvestorByUserName(investor.getUserName());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + investor.getUserName() + " already exists");
        }
        investorService.saveInvestor(investor);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        portfolioService.createPortfolio(investor);
        return ok(model);
    }
}
