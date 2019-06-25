package com.metasoft.rpiDemo.controller;

import com.google.gson.Gson;
import com.metasoft.rpiDemo.model.*;
import com.metasoft.rpiDemo.service.KeyServiceImpl;
import com.metasoft.rpiDemo.service.RoleServiceImpl;
import com.metasoft.rpiDemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private KeyServiceImpl keyServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    //region User Operation

    //User List
    @ResponseBody
    @RequestMapping(value = "/userlistAPI", method = RequestMethod.POST)
    public String returnList(@RequestParam(required = false, value = "name") String name,
                              @RequestParam( required = true, value = "pageNo") Integer pageNo)
    {
        if(pageNo== null){pageNo=0;}
        return new Gson().toJson( userServiceImpl.searchAll( name,pageNo ) );
    }

    //Add User
    @ResponseBody
    @RequestMapping(value = "/addUserAPI", method = RequestMethod.POST)
    public String addUser(@RequestBody User user )  throws Exception {
        return new Gson().toJson( userServiceImpl.addUser( user));
    }

    //User Delete
    @ResponseBody
    @RequestMapping(value = "/userDeleteAPI", method = RequestMethod.POST)
    public String deleteUser(@RequestBody User user )  throws Exception {
        return new Gson().toJson( userServiceImpl.deleteUser( user));
    }
    //Enroll environment to user
    @ResponseBody
    @RequestMapping(value = "/enrollUserKeyAPI", method = RequestMethod.POST)
    public String returnEnrollUserEnvironment(@RequestParam (required =true,value = "user_id") int user_id ,
                                              @RequestBody KeyList environmentList){

        return new Gson().toJson( userServiceImpl.enrollKey(user_id, environmentList));
    }
    //User update

    @ResponseBody
    @RequestMapping(value = "/updateUserAPI", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user){

        return new Gson().toJson( userServiceImpl.updateUser(user));
    }

    //endregion

    //region  Role Operations

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String role(){ return "role"; }

    //All Role List
    @ResponseBody
    @RequestMapping(value = "/rolesListAPI", method = RequestMethod.POST)
    public String returnRole()  throws Exception {
        return new Gson().toJson( roleServiceImpl.allRoles( ));
    }

    //Add Role
    @ResponseBody
    @RequestMapping(value = "/addRoleAPI", method = RequestMethod.POST)
    public String addRole(@RequestBody Role role )  throws Exception {
        return new Gson().toJson( roleServiceImpl.addRole( role));
    }

    //Role Delete
    @ResponseBody
    @RequestMapping(value = "/roleDeleteAPI", method = RequestMethod.POST)
    public String deleteRole(@RequestBody Role role )  throws Exception {
        return new Gson().toJson( roleServiceImpl.deleteRole( role));
    }

    //role update
    @ResponseBody
    @RequestMapping(value = "/updateRoleAPI", method = RequestMethod.POST)
    public String updateUser(@RequestBody Role role){

        return new Gson().toJson( roleServiceImpl.updateRole(role));
    }

    //endregion

    //region Key Operations
    @RequestMapping(value = "/key", method = RequestMethod.GET)
    public String key(){  return "key";    }

    //Available Key List
    @ResponseBody
    @RequestMapping(value = "/availableKeyAPI", method = RequestMethod.POST)
    public String returnAvailable()  throws Exception {
        return new Gson().toJson( keyServiceImpl.availableKey( 0 ));
    }

    // All Key List
    @ResponseBody
    @RequestMapping(value = "/keysListAPI", method = RequestMethod.POST)
    public String returnAllEnviroment()  throws Exception {
        return new Gson().toJson( keyServiceImpl.allKey( ));
    }

    @ResponseBody
    @RequestMapping(value = "/keyDeleteAPI", method = RequestMethod.POST)
    public String deleteRole(@RequestBody Key key )  throws Exception {
        return new Gson().toJson( keyServiceImpl.deleteKey( key));
    }

    //role update
    @ResponseBody
    @RequestMapping(value = "/updateKeyAPI", method = RequestMethod.POST)
    public String updateUser(@RequestBody Key key){

        return new Gson().toJson( keyServiceImpl.updateKey(key));
    }

    @ResponseBody
    @RequestMapping(value = "/addKeyAPI", method = RequestMethod.POST)
    public String addKey(@RequestBody Key key )  throws Exception {
        return new Gson().toJson( keyServiceImpl.addKey( key));
    }

    //endregion

    //region System Operation

    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public String system(){ return "system";    }

    //endregion
}
