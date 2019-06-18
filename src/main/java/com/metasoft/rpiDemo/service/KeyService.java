package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.Key;
import com.metasoft.rpiDemo.model.User;

public interface KeyService {

    ApiResponse availableKey(Integer active) throws Exception;

    ApiResponse allKey() throws Exception;

    ApiResponse updateKey(Key key);

    ApiResponse deleteKey(Key key);

    ApiResponse addKey (Key key);

}
