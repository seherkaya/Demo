package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.Environment;

public interface EnvironmentService {

    ApiResponse searchEnvironmetActive(Integer active) throws Exception;
}
