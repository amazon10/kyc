package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyCheck implements Serializable {
    private static final long serialVersionUID = -8577224110048777884L;

    private Integer id;

    private String verifyCode;

    private String excepHandle;

    private String checkRequire;

    private String handleReason;

    private String attachment;

    private String checkStatus;

    private String checkOpinion;

    private String checker;

    private String checkTime;

    private String reverifyCode;

    private String reverifyResult;

    private String sponsor;

    private String reverifyTime;
}
