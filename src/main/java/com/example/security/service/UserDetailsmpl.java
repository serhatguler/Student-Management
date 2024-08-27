package com.example.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsmpl implements UserDetails {


    private Long id;//userlari userdetailse cevirmek icin kullaniyoruz

    private String username;//unique olmak zorunda

    private String name;

    private Boolean isAdvisor;

    @JsonIgnore
    private String password;

    private String ssn;

    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsmpl(Long id, String username, String name, Boolean isAdvisor, String password,
                          String role,String ssn) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.isAdvisor = isAdvisor;
        this.password = password;
        this.ssn = ssn;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    //Securtiy de 2 tane user details oldugunu farzedelim
    // 2 userin esit olmadigini kontrol etmemiz lazim ise equals metodunu override etmemiz gerek

    public boolean equals(Object o){
        if (this==o){
            return true;
        }
        if (o==null || getClass()!=o.getClass()){
            return false;
        }
        UserDetailsmpl user = (UserDetailsmpl) o;
        return Objects.equals(id,user.getId());
    }

}
