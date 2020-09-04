package com.kyc.controller.system;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.kyc.model.RespBean;
import com.kyc.model.system.Org;
import com.kyc.service.system.OrgService;
import com.kyc.service.system.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Log
@Controller("/system")
public class OrgController {

    @Autowired
    OrgService orgService;

    @Autowired
    UserService userService;

    @PostMapping("/org/list")
    public RespBean listOrg(@RequestBody(required = false) Org org, @RequestBody(required = false) Integer pageNo,
                            @RequestBody(required = false) Integer pageSize) {
        // 获取前台发送过来的查询条件和页码
        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 10 ? 10 : pageSize;

        List<Org> orgList = orgService.listOrgs(pageNo, pageSize, org);
        if (orgList != null) {
            log.warning("fetch org information failed....");
            return RespBean.error("获取机构信息失败");
        }

        return RespBean.ok("获取机构信息成功", orgList);
    }

    @PostMapping("/org/add")
    public RespBean addOrg(@RequestBody List<Map<String, String>> orgList) {

        if (orgList.isEmpty()) {
            log.warning("there is no org information to add.");
            return RespBean.error("没有可添加的机构信息");
        }

        int result = orgService.addOrgMap(orgList);

        if (result <= 0) {
            log.warning("add org information failed....");
            return RespBean.error("增加机构信息失败");
        }

        return RespBean.ok("增加机构信息成功");
    }

    @DeleteMapping("/org/list")
    public RespBean deleteOrg(@RequestBody List<Integer> orgIds, HttpServletRequest request, HttpServerResponse response) {
        if (orgIds.isEmpty()) {
            log.warning("failed to delete org information...");
            return RespBean.error("没有可供删除的机构信息");
        }

        int result = orgService.deleteUserByOrgId(orgIds);
        if (result < 0) {
            log.warning("delete user information relation failed...");
            return RespBean.error("删除机构相关用户信息失败");
        }

        result = userService.deleteUserByOrgId(orgIds);
        if (result < 0) {
            log.warning("delete user information failed...");
            return RespBean.error("删除用户信息失败");
        }

        result = orgService.deleteOrgById(orgIds);
        if (result <= 0) {
            log.warning("delete org information failed...");
            return RespBean.error("删除机构信息失败");
        }

        return RespBean.ok("删除机构信息成功");
    }

    @PostMapping("/org/info")
    public RespBean updateOrg(@RequestBody Org org) {
        if (ObjectUtil.isEmpty(org)) {
            log.warning("there is no org information to edit...");
            return RespBean.error("没有可供修改的机构信息");
        }

        int result = orgService.updateOrg(org);
        if (result <= 0) {
            log.warning("edit org information failed...");
            return RespBean.error("机构信息修改失败");
        }

        return RespBean.ok("机构信息修改成功");
    }
}
