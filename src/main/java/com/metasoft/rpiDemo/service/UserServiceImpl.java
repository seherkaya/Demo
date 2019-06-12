package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.*;
import com.metasoft.rpiDemo.repository.EnvironmentRepository;
import com.metasoft.rpiDemo.repository.RoleRepository;
import com.metasoft.rpiDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.lang.reflect.Array;
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

    @Override
    public ApiResponse searchAll(String name, Integer pageNo) {
        ApiResponse response = new ApiResponse();

        List<User> list;
        Page page;
        CustomPageable cs ;
        if (name != null) {

            cs= new CustomPageable(userRepository.findByName( name, PageRequest.of( pageNo,2) ));
            if(cs== null){
                pageNo=pageNo-1;
            }
        } else {

            cs= new CustomPageable(userRepository.findAll(PageRequest.of( pageNo,2)));
            if(cs== null){
                pageNo=pageNo-1;
            }
        }

        for (int i = 0; i < cs.getList().size(); i++) {
            ((User) cs.getList().get( i )).setPassword( null );

        }
        response.setData( cs );
        response.setSuccessful( true );

        return response;
    }



    public ApiResponse enrollEnvironment(int user_id, int environment_id ) {
        ApiResponse response = new ApiResponse();
        User userExist =userRepository.findById( user_id );
        Environment environmentExist =environmentRepository.findById( environment_id );

        if(userExist!=null && environmentExist!=null ) {
             //kaydetme işlemleri

        }

        /*response.setData(  );*/
        response.setSuccessful( true );

        return response;
    }

}
