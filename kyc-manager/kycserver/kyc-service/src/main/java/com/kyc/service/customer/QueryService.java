package com.kyc.service.customer;

import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.VerifyInfo;

import java.util.List;
import java.util.Map;

public interface QueryService {
    Map<String, Integer> customerQueryCount();

    List<Map<String, Object>> listCustomerQueryInfo(Map<String, Object> queryMap);

    Integer addCustomerQueryInfo(Map<String, Object> map);

    List<Map<String, Object>> selectQueryList(String queryCode);

    CustomerInfo selectCompanyInfo(String customerCode);

    List<CustomerInfo> selectCustomerRelationInfo(String customerCode);

    VerifyInfo selectVeirfyInfo(String customerCode);

    Integer handleQueryExcept(Map<String, Object> exceptMap);

}
