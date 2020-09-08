package com.kyc.service.customer;

import com.kyc.model.customer.CustomerInfo;

import java.util.List;
import java.util.Map;

public interface PenetrateService {
    Map<String, Integer> customerpenetrateCount();

    List<Map<String, Object>> listCustomerPenetrateInfo(Map<String, Object> penetrateMap);

    Integer addCustomerPenetrateInfo(List<Map<String, Object>> map);

    CustomerInfo selectCompanyInfo(String customerCode);

    List<CustomerInfo> selectCustomerRelationInfo(String customerCode);
}
