package com.startup.enginizer.security;

import com.startup.enginizer.model.entities.User;
import com.startup.enginizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

    /**
     * An authentication provider, that provide auth via a datasource.
     * Created by Dragos on 10/21/2015.
     */
    @Component
    public class DatasourceAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        User retrievedUser = userRepository.getUserForCredentials(username,password);

        if(retrievedUser == null || retrievedUser.getRole() == null){
            throw new UsernameNotFoundException("Failed to authenticate user");
        }

        String role = retrievedUser.getRole().toString();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_"+role));

        org.springframework.security.core.userdetails.User userObj = new org.springframework.security.core.userdetails.User(username,password,grantedAuths);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userObj,password,grantedAuths);
        token.setDetails(retrievedUser);
        return token;
    }

    @Override
    public boolean supports(Class<? extends Object> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
