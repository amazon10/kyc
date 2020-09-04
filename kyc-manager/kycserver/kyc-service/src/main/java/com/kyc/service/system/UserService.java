package com.kyc.service.system;

import com.kyc.model.system.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers(String keywords);

    boolean updateUserRole(Integer userId, Integer[] roleIds);

    Integer deleteUserById(Integer id);

    List<User> getAllUsersExceptCurrentUser();

    Integer deleteUserByOrgId(List<Integer> orgIds);

    Integer addUser(List<Map<String, Object>> usersMap);

    Integer deleteUserById(List<Integer> userIds);

    Integer updateUser(User user);

    boolean updateUserPasswd(String oldpass, String pass, Integer userId);

    List<User> listUser(User user, Integer pageNo, Integer pageSize);
}
