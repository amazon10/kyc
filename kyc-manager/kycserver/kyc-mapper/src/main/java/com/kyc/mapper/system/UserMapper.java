package com.kyc.mapper.system;

import com.kyc.model.system.Role;
import com.kyc.model.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getUserRoleById(Integer userId);

    List<User> getAllUsers(@Param("userId") Integer userId, @Param("keywords") String keywords);

    Integer deleteByPrimaryKey(Integer id);

    List<User> getAllUsersExceptCurrentUser(Integer userId);

    User selectByPrimaryKey(Integer userId);

    Integer updatePasswd(@Param("userId") Integer userId, @Param("encodePass") String encodePass);

    Integer deleteUserByOrgId(@Param("list") List<Integer> orgIds);

    Integer addUser(@Param("userList") List<User> userList);

    Integer addOrgWithUser(@Param("userList") List<User> userList);

    Integer deleteUser(@Param("list") List<Integer> userIds);

    Integer deleteOrgWithUser(@Param("list") List<Integer> userIds);

    Integer updateUser(@Param("user") User user);

    void updateOrgWithUser(@Param("userId") Integer userId, @Param("orgId") Integer orgId);

    List<User> listUser(@Param("user") User user);
}

