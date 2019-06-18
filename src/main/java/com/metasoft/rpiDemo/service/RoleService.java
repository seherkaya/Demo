package com.metasoft.rpiDemo.service;

import com.metasoft.rpiDemo.model.ApiResponse;
import com.metasoft.rpiDemo.model.KeyList;
import com.metasoft.rpiDemo.model.Role;

public interface RoleService {

    ApiResponse allRoles () throws Exception;

    ApiResponse addRole(Role role);

    ApiResponse updateRole(Role role);

    ApiResponse deleteRole(Role role);
}
