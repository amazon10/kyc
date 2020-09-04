package com.kyc.service.system;

import com.kyc.model.system.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Integer addRole(List<Map<String, Object>> roleMap);

    List<Role> listRoles(Role role, Integer pageNo, Integer pageSize);

    Integer deleteRoleByRoleId(Integer roleId);

    Integer updateRole(Role role);
}
