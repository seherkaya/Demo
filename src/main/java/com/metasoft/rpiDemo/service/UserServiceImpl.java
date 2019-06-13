package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.*;
import com.metasoft.rpiDemo.repository.EnvironmentRepository;
import com.metasoft.rpiDemo.repository.RoleRepository;
import com.metasoft.rpiDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

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
        User userExist = userRepository.findByUserEmail(user.getUserEmail());
        if (userExist != null) {
            if (userExist.getUserPassword().equalsIgnoreCase( user.getUserPassword() )) {
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

            cs= new CustomPageable(userRepository.findByUserName( name, PageRequest.of( pageNo,2) ));
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
            ((User) cs.getList().get( i )).setUserPassword( null );

        }
        response.setData( cs );
        response.setSuccessful( true );

        return response;
    }

    @Override
    public ApiResponse enrollEnvironment(int user_id, int environment_id ) {
        ApiResponse response = new ApiResponse();
        User userExist =userRepository.findById( user_id );
        Environment environmentExist =environmentRepository.findById( environment_id );

        if(userExist!=null && environmentExist!=null ) {

            Set<Environment> environments = userExist.getEnvironment();
            if (environments == null) {
                environments = new HashSet<>();
            }

            userExist.setEnvironment(environments);

            User enrolled = userRepository.save( userExist);

            response.setSuccessful( true );
            response.setData( enrolled );
            return response;
             //kaydetme işlemleri


        } else

            response.setSuccessful( false );
        response.setMessageText( "Not founded" );

        return response;
    }

    @Override
    public ApiResponse enrollEnvironment(int user_id, EnvironmentList myArray){
        ApiResponse response = new ApiResponse();
        User userExist =userRepository.findById( user_id );


        if(userExist!=null /*&& myArray!=null*/ ) {
            Set<Environment> environments = userExist.getEnvironment();

            for (int i=0; i<myArray.getEnv().size() ;i++ ){
                Environment environmentExist =environmentRepository.findById( myArray.getEnv().get( i ).getId() );
                environments.add(environmentExist); }
                if (environments == null) {
                    environments = new HashSet<>();
                }

            userExist.setEnvironment(environments);

            User enrolled = userRepository.save( userExist);

            response.setSuccessful( true );
            response.setData( enrolled );
            return response;

        } else

            response.setSuccessful( false );
        response.setMessageText( "Not founded" );

        return response;
    }

}
