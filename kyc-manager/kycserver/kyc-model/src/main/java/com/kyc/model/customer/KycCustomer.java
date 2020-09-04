package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class KycCustomer implements Serializable {

    private static final long serialVersionUID = 4199924448155244848L;

    private Customer customer;

    private CustomerInfo customerInfo;
}
