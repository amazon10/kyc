package com.kyc.service;

import com.kyc.model.system.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenusByUserId();

    List<Menu> getAllMenus();

    List<Menu> getAllMenusWithRole();

    List<Integer> getMidsByRid(Integer rid);

    boolean updateMenuRole(Integer rid, Integer[] mids);
}
