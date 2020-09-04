package com.kyc.mapper.system;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {

    void deleteByUserid(Integer userId);

    Integer addRole(Integer userId, List<Integer> roleIds);

    List<Integer> selectUserByRoleId(Integer roleId);

    Integer deleteRoleWithUser(@Param("list") List<Integer> userIds);

}
