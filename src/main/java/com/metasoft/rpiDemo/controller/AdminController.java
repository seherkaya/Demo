package com.metasoft.rpiDemo.controller;

import com.google.gson.Gson;
import com.metasoft.rpiDemo.service.EnvironmentService;
import com.metasoft.rpiDemo.service.EnvironmentServiceImpl;
import com.metasoft.rpiDemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.lang.reflect.Array;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private EnvironmentServiceImpl environmentServiceImpl;


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

    @RequestMapping(value = "/enviromentAPI", method = RequestMethod.POST)
    public String returnEnviroment()  throws Exception {
        return new Gson().toJson( environmentServiceImpl.searchEnvironmetActive( 0 ));
    }

    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/enrollUserEnvironmentAPI", method = RequestMethod.POST)
    public String returnEnrollUserEnvironment(@RequestParam(required = true, value = "user_id") int user_id,
                                              @RequestParam( required = true, value = "environment_id") int environment_id){


        return new Gson().toJson( userServiceImpl.enrollEnvironment(user_id, environment_id));
    }
}
