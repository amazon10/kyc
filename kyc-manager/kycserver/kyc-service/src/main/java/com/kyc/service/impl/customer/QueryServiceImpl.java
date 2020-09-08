package com.kyc.service.impl.customer;

import com.kyc.mapper.customer.CustomerMapper;
import com.kyc.mapper.customer.QueryMapper;
import com.kyc.mapper.customer.VerifyMapper;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.VerifyInfo;
import com.kyc.service.customer.QueryService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log
@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    QueryMapper queryMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    VerifyMapper verifyMapper;

    @Override
    public Map<String, Integer> customerQueryCount() {
        return queryMapper.customerQueryCount();
    }

    @Override
    public List<Map<String, Object>> listCustomerQueryInfo(Map<String, Object> queryMap) {
        return queryMapper.listCustomerQueryInfo(queryMap);
    }

    @Override
    public Integer addCustomerQueryInfo(Map<String, Object> map) {
        return queryMapper.addCustomerQueryInfo(map);
    }

    @Override
    public List<Map<String, Object>> selectQueryList(String queryCode) {
        return queryMapper.selectQueryList(queryCode);
    }

    @Override
    public CustomerInfo selectCompanyInfo(String customerCode) {
        return customerMapper.selectCompanyInfo(customerCode);
    }

    @Override
    public List<CustomerInfo> selectCustomerRelationInfo(String customerCode) {
        return customerMapper.selectCustomerRelationInfo(customerCode);
    }

    @Override
    public VerifyInfo selectVeirfyInfo(String customerCode) {
        return verifyMapper.selectVerifyInfo(customerCode);
    }

    @Override
    public Integer handleQueryExcept(Map<String, Object> exceptMap) {
        return queryMapper.handleQueryExcept(exceptMap);
    }
}
