package com.metasoft.rpiDemo.controller;

import com.google.gson.Gson;
import com.metasoft.rpiDemo.model.User;
import com.metasoft.rpiDemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class InputController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String giris() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/loginAPI", method = RequestMethod.POST)
    public String permissionControl(@RequestBody User user) throws Exception {
        return new Gson().toJson( userServiceImpl.login( user));
    }

}
