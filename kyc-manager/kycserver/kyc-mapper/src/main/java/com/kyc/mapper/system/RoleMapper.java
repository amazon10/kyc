package com.kyc.mapper.system;

import com.kyc.model.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int addRole(@Param("list") List<Role> roleList);

    List<Role> listRoles(@Param("role") Role role);

    Integer deleteRoleById(@Param("roleId") Integer roleId);

    Integer updateRole(@Param("role") Role role);
}
