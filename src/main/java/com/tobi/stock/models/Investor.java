package com.tobi.stock.models;


import com.tobi.stock.enums.RoleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


/**
 * Created by Kayode Emmanuel Oluwatobi
 * Date: 25/09/2019
 * Time: 12:26 PM
 **/

@Entity
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    @NotNull(message = "Email is mandatory")
    private String email;

    @Column(unique=true)
    @NotNull(message = "Email is mandatory")
    private String userName;

    @NotNull(message = "Email is mandatory")
    private String password;

    @NotNull(message = "Email is mandatory")
    private String fullName;
    private boolean enabled;

    @Column
    @Enumerated
    @ElementCollection(targetClass = RoleType.class)
    private Set<RoleType> roles;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Set<RoleType> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleType> roles) {
        this.roles = roles;
    }

}
