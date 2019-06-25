package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.KeyList;
import com.metasoft.rpiDemo.model.User;

public interface UserService {

    User me();

    ApiResponse login(User user) throws Exception;

    ApiResponse searchAll(String name, Integer pageNo);

    ApiResponse enrollKey(int user_id, int environment_id);

    ApiResponse enrollKey(int user_id, KeyList myArray);

    ApiResponse updateUser(User user);

    ApiResponse deleteUser(User user);

    ApiResponse addUser(User user);

}
