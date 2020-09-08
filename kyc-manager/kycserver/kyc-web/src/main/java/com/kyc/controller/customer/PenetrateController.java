package com.kyc.controller.customer;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.kyc.model.RespBean;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.VerifyInfo;
import com.kyc.service.customer.PenetrateService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Log
@Controller("/customer")
public class PenetrateController {
    @Autowired
    PenetrateService penetrateService;

    @GetMapping("/penetrate/statistics")
    public RespBean customerPenetrateCount() {
        Map<String, Integer> result = penetrateService.customerpenetrateCount();
        if (result == null) {
            log.warning("customer penetrate information count failed...");
            return RespBean.error("客户受益人穿透信息统计失败");
        }

        return RespBean.ok("客户受益人穿透信息统计成功", result);
    }

    /**
     * {
     *     "customerCode": "",
     *     "industry": "",
     *     "customerType": "",
     *     "customerStatus": "",
     *     "channel": "",
     *     "processStatus": ""
     *     "customerName": "",
     *     "area": "",
     *     "customerLevel": "",
     *     "penetrateStatus": "",
     *     "startTime": "",
     *     "endTime": "",
     *     "penetrateType": "",
     *     "penetrateProcess": "",
     * }
     * @param penetrateMap
     * @return
     */
    @PostMapping("/penetrate/info")
    public RespBean listCustomerPenetrateInfo(@RequestBody Map<String, Object> penetrateMap) {
        List<Map<String, Object>> result = penetrateService.listCustomerPenetrateInfo(penetrateMap);
        if (result == null) {
            log.warning("list customer penetrate information failed...");
            return RespBean.error("获取客户受益人穿透信息失败");
        }

        return RespBean.ok("获取客户受益人穿透信息成功", result);
    }

    @PostMapping("/penetrate/add")
    public RespBean addCustomerPenetrateInfo(@RequestBody List<Map<String, Object>> penetrateMap) {
        Integer result = penetrateService.addCustomerPenetrateInfo(penetrateMap);
        if (result <= 0) {
            log.warning("add customer penetrate information failed...");
            return RespBean.error("添加客户受益人穿透信息失败");
        }

        return RespBean.ok("添加客户受益人穿透信息成功");
    }

    @PostMapping("/penetrate/relation")
    public RespBean selectPenetrate(@RequestBody Map<String, Object> queryMap) {
        String customerCode = queryMap.get("customerCode").toString();
        if (StrUtil.isEmpty(customerCode)) {
            log.warning("customer code is not provided...");
            return RespBean.error("无法获取穿透关联信息");
        }

        CustomerInfo customerInfo = penetrateService.selectCompanyInfo(customerCode);
        if (customerInfo == null) {
            log.warning("fetch customer information failed...");
            return RespBean.error("获取客户信息失败");
        }

        List<CustomerInfo> customerInfoList = penetrateService.selectCustomerRelationInfo(customerCode);
        if (customerInfoList == null) {
            log.warning("fetch customer relation information faled...");
            return RespBean.error("获取客户信息失败");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("basic", customerInfo);
        jsonObject.set("relation", customerInfoList);

        return RespBean.ok("获取穿透关联信息成功", jsonObject);

    }
}
