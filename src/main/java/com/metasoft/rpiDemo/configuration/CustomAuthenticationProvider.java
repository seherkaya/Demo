package com.metasoft.rpiDemo.configuration;

import com.metasoft.rpiDemo.model.Role;
import com.metasoft.rpiDemo.model.User;
import com.metasoft.rpiDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component

public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();


        User user = userRepository.findByEmail(name);

        if (user.getActive()!=1) {

            throw new BadCredentialsException("user not active");
        }

        for (Role role : user.getRoles()) {
            if (role.getRole().equalsIgnoreCase( "ADMIN" )) {
                return new UsernamePasswordAuthenticationToken(name, password);
            }
        }

        return new UsernamePasswordAuthenticationToken(name, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
