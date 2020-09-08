package com.kyc.mapper.customer;

import com.kyc.model.customer.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerManageMapper {

    List<Map<String, Object>> listCustomerManageInfo(Customer customer);

    Integer validCustomerStatus(@Param("list") List<Map<String, Object>> list);

    Boolean unvalidCustomerStatus(@Param("ids") List<String> ids);
}
