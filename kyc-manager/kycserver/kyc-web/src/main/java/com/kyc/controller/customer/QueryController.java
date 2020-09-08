package com.kyc.controller.customer;

import cn.hutool.json.JSONObject;
import com.kyc.model.RespBean;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.VerifyInfo;
import com.kyc.service.customer.QueryService;
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
public class QueryController {
    @Autowired
    QueryService queryService;

    @GetMapping("/query/statistics")
    public RespBean customerQueryCount() {
        Map<String, Integer> result = queryService.customerQueryCount();
        if (result == null) {
            log.warning("customer query information count failed...");
            return RespBean.error("客户深度查询信息统计失败");
        }

        return RespBean.ok("客户深度查询信息统计成功", result);
    }

    /**
     * {
     *     "queryCode": "",
     *     "customerCode": "",
     *     "customerName": "",
     *     "industry": "",
     *     "startTime": "",
     *     "endTime": "",
     *     "area": "",
     *     "customerType": "",
     *     "customerLevel": "",
     *     "customerStatus": "",
     *     "channel": "",
     *     "queryStatus": "",
     *     "queryType": "",
     *     "queryTime": "",
     *     "processStatus": ""
     * }
     * @param queryMap
     * @return
     */
    @PostMapping("/query/info")
    public RespBean listCustomerQueryInfo(@RequestBody Map<String, Object> queryMap) {
        List<Map<String, Object>> result = queryService.listCustomerQueryInfo(queryMap);
        if (result == null) {
            log.warning("list customer query information failed...");
            return RespBean.error("获取客户查询信息失败");
        }

        return RespBean.ok("获取客户查询信息成功", result);
    }

    @PostMapping("/query/except")
    public RespBean handleQueryExcept(@RequestBody Map<String, Object> queryMap) {
        Integer result = queryService.handleQueryExcept(queryMap);
        if (result <= 0) {
            log.warning("submit query except information failed...");
            return RespBean.error("异常处理提交失败");
        }

        return RespBean.ok("异常处理提交成功");
    }

    @PostMapping("/query/add")
    public RespBean addCustomerQueryInfo(@RequestBody Map<String, Object> queryMap) {
        Integer result = queryService.addCustomerQueryInfo(queryMap);
        if (result <= 0) {
            log.warning("add customer query information failed...");
            return RespBean.error("添加客户查询信息失败");
        }

        return RespBean.ok("添加客户查询信息成功");
    }

    @PostMapping("/query/list")
    public RespBean selectQueryList(@RequestBody String queryCode) {
        List<Map<String, Object>> list = queryService.selectQueryList(queryCode);
        if (list == null) {
            log.warning("select query list information failed...");
            return RespBean.error("获取客户查询项清单失败");
        }

        return RespBean.ok("获取客户查询想清单成功", list);
    }

    @PostMapping("/query/relation")
    public RespBean selectDeepQuery(@RequestBody Map<String, Object> queryMap) {
        String customerCode = queryMap.get("customerCode").toString();

        CustomerInfo customerInfo = queryService.selectCompanyInfo(customerCode);
        if (customerInfo == null) {
            log.warning("fetch customer information failed...");
            return RespBean.error("获取客户信息失败");
        }

        List<CustomerInfo> customerInfoList = queryService.selectCustomerRelationInfo(customerCode);
        if (customerInfoList == null) {
            log.warning("fetch customer relation information faled...");
            return RespBean.error("获取客户信息失败");
        }

        VerifyInfo verifyInfo = queryService.selectVeirfyInfo(customerCode);
        if (verifyInfo == null) {
            log.warning("fetch verify information failed...");
            return RespBean.error("获取核验信息失败");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("basic", customerInfo);
        jsonObject.set("relation", customerInfoList);
        jsonObject.set("verify", verifyInfo);

        return RespBean.ok("获取关联个人信息成功", jsonObject);

    }
}
