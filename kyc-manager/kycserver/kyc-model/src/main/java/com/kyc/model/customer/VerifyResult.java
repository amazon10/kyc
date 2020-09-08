package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyResult implements Serializable {
    private static final long serialVersionUID = 4563263860285960916L;

    private Integer id;

    private String verifyCode;

    private String verifyMain;

    private String templateCode;

    private String verifyRequire;

    private String verifyResult;

    private String verifyReason;
}
