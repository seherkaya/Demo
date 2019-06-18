package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.Role;
import com.metasoft.rpiDemo.repository.RoleRepository;
import com.metasoft.rpiDemo.system.SystemGeneral;
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

    @Override
    public ApiResponse addRole(Role role) {
        ApiResponse response = new ApiResponse();
        String roles = role.getRole();

        Role existingRole = roleRepository.findByRole(roles);
        if (existingRole != null) {
            if(existingRole.getActive()==0)
                role.setActive( 1 );
            response.setSuccessful( true );
            response.setMessageText( "Role exists and Role is active" );
            return response;
        }

        Role beforeSaveRole= new Role();
        beforeSaveRole.setRole( role.getRole() );


        Role savedRole = roleRepository.save( beforeSaveRole );
        response.setSuccessful( true );
        response.setData( savedRole );
        response.setMessageText( "Role created" );
        return response;
    }

    @Override
    public ApiResponse updateRole(Role role) {
        ApiResponse response = new ApiResponse();
        Role existingRole = roleRepository.findById( role.getId() );

        if (existingRole !=null){
            if (role.getRole() != null)
                existingRole.setRole( role.getRole() );
            Role savedRole = roleRepository.save( existingRole );
            response.setSuccessful( true );
            response.setData( savedRole );
            response.setMessageText( "Role is updated" );
            return response;
        } else {
            response.setSuccessful( false );
            response.setMessageText( "This Role not founded" );

            return response;
        }
    }

    @Override
    public ApiResponse deleteRole(Role role) {
        ApiResponse response =new ApiResponse( );
        roleRepository.findById( role.getId() );
        role.setActive( 0 );
        Role savedRole = roleRepository.save( role );
        response.setSuccessful( true );
        response.setData( savedRole );
        response.setMessageText( "Role deleted" );
        return response;

    }

}
