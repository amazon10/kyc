package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyRelation implements Serializable {
    private static final long serialVersionUID = -495650821550604161L;

    private Customer customer;

    private CustomerInfo customerInfo;

    private VerifyInfo verifyInfo;
}
