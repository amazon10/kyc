package com.kyc.service.impl.customer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.kyc.mapper.customer.CustomerMapper;
import com.kyc.model.customer.Customer;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.KycCustomer;
import com.kyc.service.customer.CustomerService;
import com.kyc.service.utils.ExcelUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.OperationsException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<KycCustomer> listCustomerInfo(KycCustomer kycCustomer, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return customerMapper.listCustomerInfo(kycCustomer);
    }

    @Override
    public Boolean addCustmerInfo(KycCustomer kycCustomer) {
        Boolean result = false;

        Customer customer = kycCustomer.getCustomer();
        if (ObjectUtil.isNotEmpty(customer)) {
            log.info("starting to add customer....");
            result = customerMapper.addCustomer(CollUtil.toList(customer));
            if (!result) {
                log.warning("add customer failed...");
                return result;
            }
        }

        CustomerInfo customerInfo = kycCustomer.getCustomerInfo();
        if (ObjectUtil.isNotEmpty(customerInfo)) {
            log.info("starting to add customer information...");
            result = customerMapper.addCustomerInfo(CollUtil.toList(customerInfo));
            if (!result) {
                log.warning("add customer information failed...");
                return result;
            }
        }

        return result;
    }

    @Override
    public Boolean batchImport(String fileName, MultipartFile file) throws OperationsException, IOException {

        Field[] customerFields = Customer.class.getDeclaredFields();
        Field[] customerInfoFields = CustomerInfo.class.getDeclaredFields();
        List<String> fieldList = new ArrayList<>(customerFields.length + customerInfoFields.length);

        for (Field field : customerFields) {
            fieldList.add(field.getName());
        }

        for (Field field : customerInfoFields) {
            fieldList.add(field.getName());
        }

        /** TODO: 添加Excel模板对应的字段值，需要和前端对接好字段值 */
        List<Map<String, Object>> list = ExcelUtils.leading(file, (String[]) fieldList.toArray());
        List<Customer> customers = new ArrayList<>();
        List<CustomerInfo> customerInfos = new ArrayList<>();
        list.stream().forEach(item -> {
            item.get("");
        });

        Boolean result = customerMapper.addCustomer(customers);
        if (!result) {
            log.warning("add customer failed...");
            return result;
        }

        result = customerMapper.addCustomerInfo(customerInfos);
        if (!result) {
            log.warning("add customer information failed...");
            return result;
        }

        return result;
    }
}
