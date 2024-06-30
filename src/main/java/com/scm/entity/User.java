package com.scm.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    

    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    //UserDetail will return this password so we are unabling the lombok
    @Getter(AccessLevel.NONE)
    private String password;

    @Column(length = 1000)
    private String about;

    @Column(length = 1000)
    private String profilePic;

    private String phoneNumber;

    //Same here , we are unabling the lombok here
    @Getter(value = AccessLevel.NONE)
    private boolean enabled = true;

    private boolean emailVerified = false;

    private boolean phoneVerified = false;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();


    // The getAuthorities method essentially maps the user to their roles,
    // transforming these roles into a format that Spring Security can understand
    // and use for access control.
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // list of roles[USER,ADMIN]
        // Collection of SimpGrantedAuthority[roles{ADMIN,USER}]
        Collection<SimpleGrantedAuthority> roles = roleList.stream().
                map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roles;

    }

    
    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public boolean isAccountNonExpired() {
      return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }



    @Override
    public String getUsername() {
        return this.email;
    }



}
