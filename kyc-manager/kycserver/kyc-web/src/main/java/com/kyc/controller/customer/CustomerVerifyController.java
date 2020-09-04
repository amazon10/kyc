package com.kyc.controller.customer;

import com.kyc.model.RespBean;
import com.kyc.model.customer.KycCustomer;
import com.kyc.service.customer.CustomerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log
@Controller("/customer")
public class CustomerVerifyController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/list")
    public RespBean listCustomerInfo(@RequestBody KycCustomer kycCustomer, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {

        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 0 ? 10 : pageSize;

        List<KycCustomer> kycCustomerList = customerService.listCustomerInfo(kycCustomer, pageNo, pageSize);
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

    @PostMapping
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
}
