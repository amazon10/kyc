package com.kyc.service.customer;

import com.kyc.model.customer.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerManageService {

    List<Map<String, Object>> listCustomerManageInfo(Customer customer, Integer pageNo, Integer pageSize);

    Integer validCustomerStatus(List<Map<String, Object>> list);

    Boolean unvalidCustomerStatus(List<String> ids);
}
