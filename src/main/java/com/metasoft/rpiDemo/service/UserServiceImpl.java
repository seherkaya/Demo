package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.Environment;
import com.metasoft.rpiDemo.model.Role;
import com.metasoft.rpiDemo.model.User;
import com.metasoft.rpiDemo.repository.EnvironmentRepository;
import com.metasoft.rpiDemo.repository.RoleRepository;
import com.metasoft.rpiDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EnvironmentRepository environmentRepository;

    @Override
    public ApiResponse login(User user) throws Exception {

        ApiResponse response = new ApiResponse(  );
        List<User> users = userRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        List<Environment> env = environmentRepository.findAll();
        User userExist = userRepository.findByEmail(user.getEmail());
        if (userExist != null) {
            if (userExist.getPassword().equalsIgnoreCase( user.getPassword() )) {
                for (Role r : userExist.getRoles()) {
                    if (r.getRole().equalsIgnoreCase( "ADMIN" )) {
                        response.setSuccessful( true );
                        response.setMessageText( "Login Succed" );
                        return response;
                    } else{
                        response.setSuccessful( false );
                        response.setMessageText( "User has no authorization" );
                        return response;
                    }
                }
            } else {
                response.setSuccessful( false );
                response.setMessageText( "User password is not valid or not active" );
                return response;
            }
        } else {
            response.setSuccessful( false );
            response.setMessageText( "Email is not valid" );
            return response;

        }

        response.setSuccessful( false );
        response.setMessageText( "Something's wrong" );
        return response;


    }
}
