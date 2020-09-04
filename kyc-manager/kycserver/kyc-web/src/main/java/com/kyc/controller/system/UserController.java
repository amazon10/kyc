package com.kyc.controller.system;

import com.kyc.model.RespBean;
import com.kyc.model.system.User;
import com.kyc.service.system.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log
@Controller("/system")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/info")
    public User getCurrentUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

    @PostMapping("/user/info")
    public RespBean addUser(@RequestBody List<Map<String, Object>> users) {

        if (users.isEmpty()) {
            log.warning("there is no user information to add...");
            return RespBean.error("没有可供添加的用户信息");
        }

        int result = userService.addUser(users);
        if (result <= 0) {
            log.warning("add user information failed.....");
            return RespBean.error("添加用户信息失败");
        }

        return RespBean.ok("用户信息添加成功");
    }

    @DeleteMapping("/user/info")
    public RespBean deleteUser(@RequestBody List<Integer> userIds) {
        Integer result = userService.deleteUserById(userIds);

        if (result <= 0) {
            log.warning("delete user information failed...");
            return RespBean.error("删除用户信息失败");
        }

        return RespBean.ok("删除用户信息成功");
    }

    @PostMapping("/user/edit")
    public RespBean updateUser(@RequestBody User user) {
        Integer result = userService.updateUser(user);
        if (result <= 0) {
            log.warning("update user information failed...");
            return RespBean.error("用户信息更新失败");
        }

        return RespBean.ok("用户信息更新成功");
    }

    @PostMapping("/user/password")
    public RespBean updatePasswordByUser(@RequestBody String oldpass, String newpass, Integer userId) {
        boolean result = userService.updateUserPasswd(oldpass, newpass, userId);
        if (!result) {
            log.warning("update user's password failed...");
            return RespBean.error("用户密码更新失败");
        }

        return RespBean.ok("用户密码更新成功");
    }

    @PostMapping("/user/list")
    public RespBean listUser(@RequestBody(required = false) User user, @RequestBody Integer pageNp, @RequestBody Integer pageSize) {
        List<User> userList = userService.listUser(user, pageNp, pageSize);
        if (userList == null) {
            log.warning("there is no user information to list...");
            return RespBean.error("获取用户信息失败");
        }

        return RespBean.ok("获取用户信息成功", userList);
    }
}
