package com.kyc.service.impl.system;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.kyc.mapper.system.UserMapper;
import com.kyc.mapper.system.UserRoleMapper;
import com.kyc.model.system.User;
import com.kyc.service.system.UserService;
import com.kyc.service.utils.UserUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        user.setRoleList(userMapper.getUserRoleById(user.getId()));
        return user;
    }

    @Override
    public List<User> getAllUsers(String keywords) {
        return userMapper.getAllUsers(UserUtils.getCurrentUser().getId(), keywords);
    }

    @Override
    @Transactional
    public boolean updateUserRole(Integer userId, Integer[] roleIds) {
        userRoleMapper.deleteByUserid(userId);
        return userRoleMapper.addRole(userId, CollUtil.toList(roleIds)) == roleIds.length;
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<User> getAllUsersExceptCurrentUser() {
        return userMapper.getAllUsersExceptCurrentUser(UserUtils.getCurrentUser().getId());
    }

    @Override
    public boolean updateUserPasswd(String oldpass, String pass, Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpass, user.getPassword())) {
            String encodePass = encoder.encode(pass);
            Integer result = userMapper.updatePasswd(userId, encodePass);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer deleteUserByOrgId(List<Integer> orgIds) {

        return userMapper.deleteUserByOrgId(orgIds);
    }

    @Override
    public Integer addUser(List<Map<String, Object>> usersMap) {
        List<User> userList = new ArrayList<>();

        usersMap.stream().forEach(item -> {
            User user = BeanUtil.mapToBean(item, User.class, false, CopyOptions.create());
            userList.add(user);
        });

        Integer result = userMapper.addUser(userList);
        if (result <= 0) {
            log.warning("user information added failed...");
            return -1;
        }

        result = userMapper.addOrgWithUser(userList);
        if (result <= 0) {
            log.warning("org informaton added failed with user information...");
            return -1;
        }

        userList.stream().forEach(item -> {
            if (CollUtil.isNotEmpty(item.getRoleIds())) {
                userRoleMapper.addRole(item.getId(), item.getRoleIds());
            }
        });

        return result;
    }

    @Override
    public Integer deleteUserById(List<Integer> userIds) {
        Integer result = userRoleMapper.deleteRoleWithUser(userIds);
        if (result < 0) {
            log.warning("delete user and role relationship failed...");
            return -1;
        }

        result = userMapper.deleteOrgWithUser(userIds);
        if (result < 0) {
            log.warning("delete user and org relationship failed....");
            return -1;
        }

        result = userMapper.deleteUser(userIds);
        if (result < 0) {
            log.warning("delete user information failed...");
            return -1;
        }

        return result;
    }

    @Override
    public Integer updateUser(User user) {
        List<Integer> roleIds = user.getRoleIds();
        Integer userId = user.getId();
        if (CollUtil.isNotEmpty(roleIds)) {
            updateUserRole(userId, (Integer[]) roleIds.toArray());
        }

        Integer orgId = user.getOrgId();
        if (ObjectUtil.isNotEmpty(orgId)) {
            userMapper.updateOrgWithUser(userId, orgId);
        }

        return userMapper.updateUser(user);
    }

    @Override
    public List<User> listUser(User user, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return userMapper.listUser(user);
    }
}
