package com.kyc.service.customer;

import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.KycCustomer;
import org.springframework.web.multipart.MultipartFile;

import javax.management.OperationsException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<KycCustomer> listCustomers(KycCustomer kycCustomer, Integer pageNo, Integer pageSize);

    Boolean addCustmerInfo(KycCustomer kycCustomer);

    Boolean batchImport(String fileName, MultipartFile file) throws OperationsException, IOException;

    Map<String, Integer> customerPushStatusCount(String mode);

    Integer updateQueryRelation(CustomerInfo customerInfo);

    Integer addQueryRelation(CustomerInfo customerInfo);
}
