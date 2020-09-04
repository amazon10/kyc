package com.kyc.service.customer;

import com.kyc.model.customer.KycCustomer;
import org.springframework.web.multipart.MultipartFile;

import javax.management.OperationsException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {

    List<KycCustomer> listCustomerInfo(KycCustomer kycCustomer, Integer pageNo, Integer pageSize);

    Boolean addCustmerInfo(KycCustomer kycCustomer);

    Boolean batchImport(String fileName, MultipartFile file) throws OperationsException, IOException;
}
