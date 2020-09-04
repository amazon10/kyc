package com.kyc.controller.system;

import com.kyc.model.RespBean;
import com.kyc.model.system.Menu;
import com.kyc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/system")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menus")
    public RespBean listMenus() {
        List<Menu> menuList = menuService.getAllMenus();

        return RespBean.ok("获取权限列表成功", menuList);
    }

}
