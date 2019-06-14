package com.metasoft.rpiDemo.controller;

import com.google.gson.Gson;
import com.metasoft.rpiDemo.model.EnvironmentList;
import com.metasoft.rpiDemo.model.User;
import com.metasoft.rpiDemo.service.EnvironmentService;
import com.metasoft.rpiDemo.service.EnvironmentServiceImpl;
import com.metasoft.rpiDemo.service.RoleServiceImpl;
import com.metasoft.rpiDemo.service.UserServiceImpl;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private EnvironmentServiceImpl environmentServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }



    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/listAPI", method = RequestMethod.POST)
    public String returnList(@RequestParam(required = false, value = "name") String name,
                              @RequestParam( required = true, value = "pageNo") Integer pageNo)
    {if(pageNo== null){pageNo=0;}
        return new Gson().toJson( userServiceImpl.searchAll( name,pageNo ) );
    }

    @ResponseBody
    @RequestMapping(value = "/enviromentAPI", method = RequestMethod.POST)
    public String returnEnviroment()  throws Exception {
        return new Gson().toJson( environmentServiceImpl.searchEnvironmetActive( 0 ));
    }

    @ResponseBody
    @RequestMapping(value = "/rolesAPI", method = RequestMethod.POST)
    public String returnRole()  throws Exception {
        return new Gson().toJson( roleServiceImpl.allRoles( ));
    }

    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/enrollUserEnvironmentAPI", method = RequestMethod.POST)
    public String returnEnrollUserEnvironment(@RequestParam (required =true,value = "user_id") int user_id ,
                                              @RequestBody  EnvironmentList environmentList){


        return new Gson().toJson( userServiceImpl.enrollEnvironment(user_id, environmentList));
    }

    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/updateUserAPI", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user){


        return new Gson().toJson( userServiceImpl.updateUser(user));
    }
}
