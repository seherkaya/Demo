package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.Environment;
import com.metasoft.rpiDemo.repository.EnvironmentRepository;
import com.metasoft.rpiDemo.repository.RoleRepository;
import com.metasoft.rpiDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("environmentService")
public class EnvironmentServiceImpl implements EnvironmentService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EnvironmentRepository environmentRepository;

    @Override
    public ApiResponse searchEnvironmetActive(Integer active) throws Exception {

        ApiResponse response = new ApiResponse(  );

        List<Environment> environmentExist = environmentRepository.findAllByActive(active);
        if(environmentExist!=null){
            response.setData( environmentExist );
            response.setSuccessful( true );
            response.setMessageText( "Succeed" );
            return response;
        }
        else {

            response.setSuccessful( false );
            response.setMessageText( "Something's wrong" );
            return response;

        }



    }
}
