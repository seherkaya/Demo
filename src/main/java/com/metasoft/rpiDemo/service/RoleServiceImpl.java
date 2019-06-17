package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.Role;
import com.metasoft.rpiDemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ApiResponse allRoles() throws Exception {
        ApiResponse response = new ApiResponse(  );

        List<Role> roleExist = roleRepository.findAllBy();
        if(roleExist!=null){
            response.setData( roleExist );
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
