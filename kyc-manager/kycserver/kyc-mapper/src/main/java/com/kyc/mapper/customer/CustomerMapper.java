package com.kyc.mapper.customer;

import com.kyc.model.customer.Customer;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.KycCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    List<KycCustomer> listCustomerInfo(@Param("kycCustomer") KycCustomer kycCustomer);

    Boolean addCustomer(@Param("customers") List<Customer> customers);

    Boolean addCustomerInfo(@Param("customerInfos") List<CustomerInfo> customerInfos);

}
