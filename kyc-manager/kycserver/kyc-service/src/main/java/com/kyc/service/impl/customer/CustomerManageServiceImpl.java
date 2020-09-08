package com.kyc.service.impl.customer;

import com.github.pagehelper.PageHelper;
import com.kyc.mapper.customer.CustomerManageMapper;
import com.kyc.model.customer.Customer;
import com.kyc.service.customer.CustomerManageService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log
@Service
public class CustomerManageServiceImpl implements CustomerManageService {

    @Autowired
    CustomerManageMapper customerManageMapper;

    @Override
    public List<Map<String, Object>> listCustomerManageInfo(Customer customer, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return customerManageMapper.listCustomerManageInfo(customer);
    }

    @Override
    public Integer validCustomerStatus(List<Map<String, Object>> list) {
        return customerManageMapper.validCustomerStatus(list);
    }

    @Override
    public Boolean unvalidCustomerStatus(List<String> ids) {
        return customerManageMapper.unvalidCustomerStatus(ids);
    }
}
