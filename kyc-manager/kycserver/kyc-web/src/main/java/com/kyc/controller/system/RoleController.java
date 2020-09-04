package com.kyc.controller.system;

import cn.hutool.core.util.ObjectUtil;
import com.kyc.model.RespBean;
import com.kyc.model.system.Role;
import com.kyc.service.system.RoleService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Log
@Controller("/system")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/role/add")
    public RespBean addRole(@RequestBody List<Map<String, Object>> roleMap) {
        if (roleMap.isEmpty()) {
            log.warning("there is no role information to add...");
            return RespBean.error("没有可供添加的角色信息");
        }

        int result = roleService.addRole(roleMap);
        if (result <= 0) {
            log.warning("add role information failed...");
            return RespBean.error("角色信息添加失败");
        }

        return RespBean.ok("添加角色信息成功");
    }

    @PostMapping("/role/list")
    public RespBean listRoles(@RequestBody(required = false) Role role, @RequestBody(required = false) Integer pageNo,
                              @RequestBody(required = false) Integer pageSize) {
        // 获取前台发送过来的查询条件和页码
        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 10 ? 10 : pageSize;

        List<Role> roles = roleService.listRoles(role, pageNo, pageSize);

        return RespBean.ok("获取角色信息成功", roles);
    }

    @DeleteMapping("/role/info/${roleId}")
    public RespBean deleteRole(@PathVariable("roleId") Integer roleId) {
        int result = roleService.deleteRoleByRoleId(roleId);
        if (result == -1) {
            log.warning("there is some user information under role, so can not delete it...");
            return RespBean.error("您要删除的角色下有匹配用户，无法删除");
        } else if (result < 0) {
            log.warning("delete role information failed...");
            return RespBean.error("删除角色信息失败");
        }

        return RespBean.ok("删除角色信息成功");
    }

    @PostMapping("/role/edit")
    public RespBean updateRole(@RequestBody Role role) {
        if (ObjectUtil.isEmpty(role)) {
            log.warning("there is no role information to edit...");
            return RespBean.error("没有可供修改的角色信息");
        }

        Integer result = roleService.updateRole(role);
        if (result <= 0) {
            log.warning("update role information failed...");
            return RespBean.error("角色信息更新失败");
        }

        return RespBean.ok("角色信息更新成功");
    }
}
