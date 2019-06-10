package com.metasoft.rpiDemo.controller;

import com.google.gson.Gson;
import com.metasoft.rpiDemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/listeAPI", method = RequestMethod.POST)
    public String listeDondur(@RequestParam(required = false, value = "name") String name,
                              @RequestParam( required = true, value = "pageNo") Integer pageNo)
    {if(pageNo== null){pageNo=0;}
        return new Gson().toJson( userServiceImpl.searchAll( name,pageNo ) );
    }
}
