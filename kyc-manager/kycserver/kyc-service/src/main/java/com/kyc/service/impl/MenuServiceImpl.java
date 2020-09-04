package com.kyc.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.kyc.mapper.system.MenuMapper;
import com.kyc.mapper.system.MenuRoleMapper;
import com.kyc.model.system.Menu;
import com.kyc.model.system.User;
import com.kyc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "menus_cache")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Override
    public List<Menu> getMenusByUserId() {

        return menuMapper.getMenusByUserId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    @Override
    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    @Override
    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        if (mids == null || mids.length == 0) {
            return true;
        }
        Integer result = menuRoleMapper.insertRecord(rid, CollUtil.toList(mids));
        return result==mids.length;
    }
}
