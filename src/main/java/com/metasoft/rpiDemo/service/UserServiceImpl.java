package com.metasoft.rpiDemo.service;

import com.google.gson.Gson;
import com.metasoft.rpiDemo.model.*;
import com.metasoft.rpiDemo.repository.KeyRepository;
import com.metasoft.rpiDemo.repository.RoleRepository;
import com.metasoft.rpiDemo.repository.UserRepository;
import com.metasoft.rpiDemo.system.SystemGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private KeyRepository keyRepository;

    @Override
    public User me() {

        boolean hidePassword=true ;
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof User) {
            User user = (User) auth.getPrincipal();
            if (hidePassword) user.setUserPassword(null);
            return user;
        }
        Gson gson = new Gson();
        User _user = userRepository.findByUserEmail(auth.getName());
        User clone = gson.fromJson(gson.toJson(_user), User.class);
        if (hidePassword) clone.setUserPassword(null);
//        user.setPassword(null);
        return clone;
    }

    @Override
    public ApiResponse login(User user) throws Exception {

        User WhoamI= me();

        ApiResponse response = new ApiResponse( );

        List<User> users = userRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        List<Key> env = keyRepository.findAll();

        User existingUser = userRepository.findByUserEmail(user.getUserEmail());
        if (existingUser != null) {
            if (existingUser.getUserPassword().equalsIgnoreCase( user.getUserPassword() )) {
                for (Role r : existingUser.getRoles()) {
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
    public ApiResponse addUser(User user) {
        ApiResponse response = new ApiResponse();
        String email = user.getUserEmail();

        if (!SystemGeneral.validateEmail( email )) {
            response.setSuccessful( false );
            response.setMessageText( "Email is not valid" );
            return response;
        }

        User existingUser = userRepository.findByUserEmail( email );
        if (existingUser != null) {
            response.setSuccessful( false );
            response.setMessageText( "User exists" );
            return response;
        }

        User beforeSaveUser = new User();
        beforeSaveUser.setUserName( user.getUserName() );
        beforeSaveUser.setUserSurname( user.getUserSurname() );
        beforeSaveUser.setUserEmail( user.getUserEmail() );
        beforeSaveUser.setTc( user.getTc() );
        beforeSaveUser.setUserPassword( user.getUserPassword());
        beforeSaveUser.setActive( 1 );

        Set<Role> userRole = roleRepository.findByRole( user.getUserRoles() );
        beforeSaveUser.setRoles( new HashSet<Role>( userRole ) );

        User savedUser = userRepository.save( beforeSaveUser );
        response.setSuccessful( true );
        response.setData( savedUser );
        return response;
    }

    @Override
    public ApiResponse searchAll(String name, Integer pageNo) {
        ApiResponse response = new ApiResponse();

        List<User> list;
        Page page;
        CustomPageable cs ;
        if (name != null) {

            cs = new CustomPageable( userRepository.findByActiveAndUserNameIsContaining( 1, name, PageRequest.of( pageNo, 2 ) ) );
            if(cs== null){
                pageNo=pageNo-1;
            }
        } else {

            cs = new CustomPageable( userRepository.findAllByActive( 1, PageRequest.of( pageNo, 2 ) ) );
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
    public ApiResponse enrollKey(int userId, int keyId) {
        ApiResponse response = new ApiResponse();
        User existingUser = userRepository.findById( userId );
        Key existingKey = keyRepository.findById( keyId );


        if (existingUser != null && existingKey != null) {
            existingKey.setActive( 1 );
            Set<Key> keys = existingUser.getKey();

            if (keys == null) {
                keys = new HashSet<>();

            }

            existingUser.setKey( keys );

            User enrolled = userRepository.save( existingUser);

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
    public ApiResponse enrollKey(int user_id, KeyList myArray) {
        ApiResponse response = new ApiResponse();
        User existingUser =userRepository.findById( user_id );


        if(existingUser!=null /*&& myArray!=null*/ ) {
            Set<Key> keys = existingUser.getKey();

            for (int i=0; i<myArray.getEnv().size() ;i++ ){
                Key existingKey = keyRepository.findById( myArray.getEnv().get( i ).getId() );
                existingKey.setActive( 1 );
                keys.add( existingKey );
            }
            if (keys == null) {
                keys = new HashSet<>();
                }

            existingUser.setKey( keys );

            User enrolled = userRepository.save( existingUser);

            response.setSuccessful( true );
            response.setData( enrolled );
            response.setMessageText( "Key is enrolled" );
            return response;

        } else

            response.setSuccessful( false );
            response.setMessageText( "Not founded" );

        return response;
    }

    @Override
    public ApiResponse updateUser(User user){
        ApiResponse response = new ApiResponse();
        User existingUser = userRepository.findById( user.getId() );

        if (existingUser !=null){

            if (user.getUserName() != null)
                existingUser.setUserName( user.getUserName() );
            if (user.getUserSurname() != null)
                existingUser.setUserSurname( user.getUserSurname() );
            if (user.getUserEmail() != null){
                if (!SystemGeneral.validateEmail( user.getUserEmail() )) {
                    response.setSuccessful( false );
                    response.setMessageText( "Email is not valid" );
                    return response;
                }
            }
            existingUser.setUserEmail( user.getUserEmail() );
            if (user.getUserPassword() != null)
                existingUser.setUserPassword(/* bCryptPasswordEncoder.encode(*/user.getUserPassword()/*)*/);
            if (user.getTc() != null)
                existingUser.setTc( user.getTc() );
            if ( user.getActive() != 0)
                existingUser.setActive( user.getActive() );
            if(user.getRoles()!=null){
                existingUser.setUserRoles(user.getUserRoles());
            }
            User savedUser = userRepository.save( existingUser );
            response.setSuccessful( true );
            response.setData( savedUser );
            response.setMessageText( "User is updated" );
            return response;
        } else {
            response.setSuccessful( false );
            response.setMessageText( "This User not founded" );

            return response;
        }
    }

    @Override
    public ApiResponse deleteUser(User user) {
        ApiResponse response =new ApiResponse( );
        userRepository.findById( user.getId() );
        user.setActive( 0 );
        User savedUser = userRepository.save( user );
        response.setSuccessful( true );
        response.setData( savedUser );
        return response;
    }

}
