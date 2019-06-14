package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;

public interface EnvironmentService {

    ApiResponse searchEnvironmetActive(Integer active) throws Exception;
}
