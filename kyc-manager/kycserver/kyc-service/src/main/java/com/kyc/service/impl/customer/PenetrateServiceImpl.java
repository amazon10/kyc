package com.kyc.service.impl.customer;

import com.kyc.mapper.customer.CustomerMapper;
import com.kyc.mapper.customer.PenetrateMapper;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.service.customer.PenetrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PenetrateServiceImpl implements PenetrateService {

    @Autowired
    PenetrateMapper penetrateMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public Map<String, Integer> customerpenetrateCount() {
        return penetrateMapper.customerpenetrateCount();
    }

    @Override
    public List<Map<String, Object>> listCustomerPenetrateInfo(Map<String, Object> penetrateMap) {
        return penetrateMapper.listCustomerPenetrateInfo(penetrateMap);
    }

    @Override
    public Integer addCustomerPenetrateInfo(List<Map<String, Object>> map) {
        return penetrateMapper.addCustomerPenetrateInfo(map);
    }

    @Override
    public CustomerInfo selectCompanyInfo(String customerCode) {
        return customerMapper.selectCompanyInfo(customerCode);
    }

    @Override
    public List<CustomerInfo> selectCustomerRelationInfo(String customerCode) {
        return customerMapper.selectCustomerRelationInfo(customerCode);
    }
}
