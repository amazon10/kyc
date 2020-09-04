package com.kyc.service.impl.system;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.github.pagehelper.PageHelper;
import com.kyc.mapper.system.OrgMapper;
import com.kyc.model.system.Org;
import com.kyc.service.system.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    OrgMapper orgMapper;

    @Override
    public List<Org> listOrgs(Integer pageNo, Integer pageSize, Org org) {
        PageHelper.startPage(pageNo, pageSize);
        return orgMapper.listOrgs(org);
    }

    @Override
    public Integer addOrgMap(List<Map<String, String>> orgMap) {
        List<Org> orgList = new ArrayList<>();
        orgMap.forEach(item -> {
            Org org = BeanUtil.mapToBean(item, Org.class, false, CopyOptions.create().ignoreError());
            orgList.add(org);
        });

        return orgMapper.addOrgList(orgList);
    }

    @Override
    public Integer deleteOrgById(List<Integer> orgIds) {
        return orgMapper.deleteOrgById(orgIds);
    }

    @Override
    public Integer deleteUserByOrgId(List<Integer> orgIds) {
        return orgMapper.deleteUserByOrgId(orgIds);
    }

    @Override
    public Integer updateOrg(Org org) {
        return orgMapper.updateOrg(org);
    }
}
