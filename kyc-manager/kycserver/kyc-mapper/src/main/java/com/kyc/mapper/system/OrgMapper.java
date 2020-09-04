package com.kyc.mapper.system;

import com.kyc.model.system.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrgMapper {
    List<Org> listOrgs(Org org);

    Integer addOrgList(@Param("list") List<Org> orgList);

    Integer deleteOrgById(@Param("list") List<Integer> orgIds);

    Integer deleteUserByOrgId(@Param("list") List<Integer> orgIds);

    Integer updateOrg(@Param("org") Org org);
}
