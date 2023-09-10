package com.eazybytes.soringsecuritybasic.config;

import com.eazybytes.soringsecuritybasic.Model.Authorities;
import com.eazybytes.soringsecuritybasic.Model.Customer;
import com.eazybytes.soringsecuritybasic.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(username);
        if(customer != null) {
            if(passwordEncoder.matches(password, customer.getPwd())) {
                return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthories(customer.getAuthorities()));
            }
            else {
                throw new BadCredentialsException("Invalid password");
            }
        }
        else {
            throw new UsernameNotFoundException("Invalid User");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public List<GrantedAuthority> getGrantedAuthories(Set<Authorities> authorities) {
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        for(Authorities auth: authorities) {
            grantedAuthority.add(new SimpleGrantedAuthority(auth.getName()));
        }
        return grantedAuthority;
    }
}
