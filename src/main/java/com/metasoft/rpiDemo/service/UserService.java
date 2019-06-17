package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.Environment;
import com.metasoft.rpiDemo.model.EnvironmentList;
import com.metasoft.rpiDemo.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    ApiResponse login(User user) throws Exception;

    ApiResponse searchAll(String name, Integer pageNo);

    ApiResponse enrollEnvironment(int user_id, int environment_id);

    ApiResponse enrollEnvironment(int user_id, EnvironmentList myArray);

    ApiResponse updateUser(User user);

    ApiResponse deleteUser(User user);

}
