package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.User;

public interface UserService {

    ApiResponse login(User user) throws Exception;

    ApiResponse searchAll(String name, Integer pageNo);

}
