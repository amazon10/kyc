package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyInfo implements Serializable {
    private static final long serialVersionUID = -1161030171853786840L;

    private Integer verifyId;

    private String verifyCode;

    private String verifyStatus;

    private String verifyResult;

    private String verifyTime;

    private String verifyCount;

    private String processStatus;

    private String verifyMode;

    private String templateCode;

    private String customerCode;

    private String startTime;

    private String endTime;

}
