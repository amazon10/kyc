package com.kyc.controller.customer;

import com.kyc.model.RespBean;
import com.kyc.model.customer.Customer;
import com.kyc.service.customer.CustomerManageService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Log
@Controller("/customer")
public class CustomerManageController {

    @Autowired
    CustomerManageService customerManageService;

    @PostMapping("/manage")
    public RespBean listCustomerManageInfo(@RequestBody Customer customer, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {
        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 0 ? 10 : pageSize;

        List<Map<String, Object>> customerList = customerManageService.listCustomerManageInfo(customer, pageNo, pageSize);
        if (customerList == null) {
            log.warning("list customer manage information failed...");
            return RespBean.error("获取客户信息管理列表失败");
        }

        return RespBean.ok("获取客户信息管理列表成功", customerList);
    }

    @PostMapping("/manage/valid")
    public RespBean validCustomerStatus(@RequestBody List<Map<String, Object>> list) {
        Integer result = customerManageService.validCustomerStatus(list);
        if (result <= 0) {
            log.warning("valid customer status failed...");
            return RespBean.error("标记客户状态失败");
        }

        return RespBean.ok("标记客户状态成功");
    }

    @PostMapping("/manage/unvalid")
    public RespBean unvalidCustomerStatus(@RequestBody List<String> customers) {
        Boolean result = customerManageService.unvalidCustomerStatus(customers);
        if (!result) {
            log.warning("unvalid customer status failed...");
            return RespBean.error("取消标记客户状态失败");
        }

        return RespBean.ok("取消标记客户状态成功");
    }

}
