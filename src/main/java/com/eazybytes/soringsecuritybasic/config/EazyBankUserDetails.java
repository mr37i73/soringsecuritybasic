package com.eazybytes.soringsecuritybasic.config;

import com.eazybytes.soringsecuritybasic.Model.Customer;
import com.eazybytes.soringsecuritybasic.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EazyBankUserDetails implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password=null;
        List<GrantedAuthority> authorities=new ArrayList<>();
        Customer customer = customerRepository.findByEmail(username);
        if(customer == null) {
            throw new UsernameNotFoundException("User not found "+username);
        }
        else {
            userName = customer.getEmail();
            password = customer.getPwd();
            authorities.add(new SimpleGrantedAuthority(customer.getRole()));
        }
        return new User(userName,password,authorities);
    }
}
