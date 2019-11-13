package com.tobi.stock.services;

import com.tobi.stock.enums.RoleType;
import com.tobi.stock.models.Investor;
import com.tobi.stock.repositories.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 2:08 PM
 **/

@Service
public class InvestorService implements UserDetailsService {

    private InvestorRepository investorRepository;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserRepository(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveInvestor(Investor investor) {
        investor.setPassword(bCryptPasswordEncoder.encode(investor.getPassword()));
        investor.setEnabled(true);

        investor.setRoles(new HashSet<>(Arrays.asList(RoleType.BASIC)));
        investorRepository.save(investor);
    }


    public Investor findInvestorByEmail(String email) {
        return investorRepository.findByEmail(email);
    }

    public Investor findInvestorByUserName(String userName) {
        return investorRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Investor investor = investorRepository.findByUserName(userName);
        if(investor != null) {
            List<GrantedAuthority> authorities = getUserAuthority(investor.getRoles());
            return buildUserForAuthentication(investor, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<RoleType> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.toString()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Investor investor, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(investor.getUserName(), investor.getPassword(), authorities);
    }
}
