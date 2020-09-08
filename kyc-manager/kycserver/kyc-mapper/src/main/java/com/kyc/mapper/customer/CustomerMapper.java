package com.kyc.mapper.customer;

import com.kyc.model.customer.Customer;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.KycCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    List<KycCustomer> listCustomers(@Param("kycCustomer") KycCustomer kycCustomer);

    Boolean addCustomer(@Param("customers") List<Customer> customers);

    Boolean addCustomerInfo(@Param("customerInfos") List<CustomerInfo> customerInfos);

    Map<String, Integer> customerPushStatusCount(@Param("mode") String mode);

    List<CustomerInfo> getCustomerInfoByCustomerCode(@Param("customerCode") String customerCode);

    Integer updateQueryRelation(@Param("customerInfo") CustomerInfo customerInfo);

    Integer addQueryRelation(@Param("info") CustomerInfo customerInfo);

    CustomerInfo selectCompanyInfo(@Param("customerCode") String customerCode);

    List<CustomerInfo> selectCustomerRelationInfo(@Param("customerCode") String customerCode);
}
