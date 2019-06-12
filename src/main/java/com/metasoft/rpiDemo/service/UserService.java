package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.User;

import java.lang.reflect.Array;

public interface UserService {

    ApiResponse login(User user) throws Exception;

    ApiResponse searchAll(String name, Integer pageNo);

    ApiResponse enrollEnvironment(int user_id, int environment_id);

}
