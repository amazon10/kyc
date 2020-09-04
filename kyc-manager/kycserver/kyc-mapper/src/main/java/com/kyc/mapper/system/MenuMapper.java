package com.kyc.mapper.system;

import com.kyc.model.system.Menu;

import java.util.List;

public interface MenuMapper {

    List<Menu> getMenusByUserId(Integer userId);

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);

    List<Menu> getAllMenusWithRole();
}
