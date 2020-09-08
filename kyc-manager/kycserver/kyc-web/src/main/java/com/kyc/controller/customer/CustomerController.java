package com.kyc.controller.customer;

import cn.hutool.core.collection.CollUtil;
import com.kyc.model.RespBean;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.KycCustomer;
import com.kyc.service.customer.CustomerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Log
@Controller("/cutomer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/list")
    public RespBean listCustomerInfo(@RequestBody KycCustomer kycCustomer, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {

        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 0 ? 10 : pageSize;

        List<KycCustomer> kycCustomerList = customerService.listCustomers(kycCustomer, pageNo, pageSize);
        if (kycCustomerList == null) {
            log.warning("select customer information failed...");
            return RespBean.error("获取客户信息失败");
        }

        return RespBean.ok("获取客户信息成功", kycCustomerList);
    }

    @PostMapping("/info")
    public RespBean addCustomerInfo(@RequestBody KycCustomer kycCustomer) {
        Boolean result = customerService.addCustmerInfo(kycCustomer);
        if (!result) {
            log.warning("add customer information failed...");
            return RespBean.error("添加客户信息失败");
        }

        return RespBean.ok("添加客户信息成功");
    }

    @PostMapping("/batch/import")
    public RespBean batchImport(@RequestBody MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Boolean result = false;

        try {
            result = customerService.batchImport(fileName, file);
        } catch (Exception e) {
            log.warning(String.format("excel file import failed -> %s", e.getMessage()));
        }

        if (!result) {
            log.warning("batch import customer information faield...");
            return RespBean.error("批量导入客户信息失败");
        }

        return RespBean.ok("批量导入客户信息成功");
    }

    @GetMapping("/push/count")
    public RespBean customerPushStatusCount(@RequestBody String customerMode) {
        Map<String, Integer> map = customerService.customerPushStatusCount(customerMode);
        if (CollUtil.isEmpty(map)) {
            log.warning("count customer information push status failed...");
            return RespBean.error("客户信息入库状态统计失败");
        }

        return RespBean.ok("客户信息入库状态统计成功", map);
    }

    @PostMapping("/query/relation")
    public RespBean updateqQueryRelation(@RequestBody CustomerInfo customerInfo) {
        Integer result = customerService.updateQueryRelation(customerInfo);
        if (result <= 0) {
            log.warning("update customer information failed...");
            return RespBean.error("客户信息更新失败");
        }

        return RespBean.ok("客户信息更新成功");
    }

    @PostMapping("/query/relation/add")
    public RespBean addqQueryRelation(@RequestBody CustomerInfo customerInfo) {
        Integer result = customerService.addQueryRelation(customerInfo);
        if (result <= 0) {
            log.warning("update customer information failed...");
            return RespBean.error("客户信息添加失败");
        }

        return RespBean.ok("客户信息添加成功");
    }
}
