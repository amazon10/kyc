package com.kyc.service.impl.system;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.kyc.mapper.system.MenuRoleMapper;
import com.kyc.mapper.system.RoleMapper;
import com.kyc.mapper.system.UserRoleMapper;
import com.kyc.model.system.Role;
import com.kyc.service.system.RoleService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public Integer addRole(List<Map<String, Object>> roleMap) {
        List<Role> roles = new ArrayList<>();
        roleMap.stream().forEach(item -> {
            Role role = BeanUtil.mapToBean(item, Role.class, false, CopyOptions.create().ignoreError());
            roles.add(role);
        });

        int result = roleMapper.addRole(roles);
        if (result <= 0) {
            log.warning("add role information failed...");
            return 0;
        }

        roles.stream().forEach(item -> {
            menuRoleMapper.insertRecord(item.getId(), item.getMenuIds());
        });

        return result;
    }

    @Override
    public List<Role> listRoles(Role role, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return roleMapper.listRoles(role);
    }

    @Override
    public Integer deleteRoleByRoleId(Integer roleId) {
        List<Integer> userIds = userRoleMapper.selectUserByRoleId(roleId);
        if (CollUtil.isNotEmpty(userIds)) {
            log.warning("there is some user information with role, so can not delete it...");
            return -1;
        }

        return roleMapper.deleteRoleById(roleId);
    }

    @Override
    public Integer updateRole(Role role) {
        return roleMapper.updateRole(role);
    }
}
