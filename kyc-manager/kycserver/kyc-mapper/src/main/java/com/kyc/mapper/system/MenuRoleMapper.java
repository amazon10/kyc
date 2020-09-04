package com.kyc.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyc.model.system.MenuRole;

import java.util.List;

public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    void deleteByRid(Integer rid);

    Integer insertRecord(Integer rid, List<Integer> mids);
}
