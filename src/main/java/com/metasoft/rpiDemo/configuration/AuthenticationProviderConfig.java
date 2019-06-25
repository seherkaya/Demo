package com.metasoft.rpiDemo.configuration;

import com.metasoft.rpiDemo.model.User;
import com.metasoft.rpiDemo.model.Role;
import com.metasoft.rpiDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationProviderConfig implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        int ia;
        User user = userRepository.findByUserEmail( name );

        if (user==null) {

            throw new BadCredentialsException( "User not Founded" );
        }
            // use the credentials
            // and authenticate against the third-party system

       /* if( password != user.getUserPassword()){

            throw new BadCredentialsException( "Password not valid" );

           *//* return new UsernamePasswordAuthenticationToken( name, password);*//*
        }*/
        if(user.getActive()!=1){
            throw new BadCredentialsException( "User not active" );

        }


        return new UsernamePasswordAuthenticationToken( name, password, user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);

    }
}
