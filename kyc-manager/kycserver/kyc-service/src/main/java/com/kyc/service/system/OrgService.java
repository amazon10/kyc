package com.kyc.service.system;

import com.kyc.model.system.Org;

import java.util.List;
import java.util.Map;

public interface OrgService {
    List<Org> listOrgs(Integer pageNo, Integer pageSize, Org org);

    Integer addOrgMap(List<Map<String, String>> orgMap);

    Integer deleteOrgById(List<Integer> orgIds);

    Integer deleteUserByOrgId(List<Integer> orgIds);

    Integer updateOrg(Org org);
}
