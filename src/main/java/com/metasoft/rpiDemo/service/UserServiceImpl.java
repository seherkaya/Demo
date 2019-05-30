package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.User;
import com.metasoft.rpiDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse login(User user) throws Exception {
        ApiResponse response = new ApiResponse(  );
        User userExist = userRepository.findByEmail(user.getEmail());
        if (userExist != null) {
            if(userExist.getRoles().toString()=="ADMIN" && userExist.getPassword().equalsIgnoreCase( user.getPassword() ) ){
                response.setSuccessful( true );
                response.setMessageText( "Login Succed" );
                return response;
            }
            else {
                response.setSuccessful( false );
                response.setMessageText( "User password is not valid or not active" );
                return response;
            }
        } else {
            response.setSuccessful( false );
            response.setMessageText( "Email is not valid" );
            return response;

        }
    }
}
