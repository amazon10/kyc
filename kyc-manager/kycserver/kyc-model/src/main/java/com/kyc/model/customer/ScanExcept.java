package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScanExcept implements Serializable {
    private static final long serialVersionUID = 8018185502665781923L;

    private Integer id;

    private String scanCode;

    private String exceptHandle;

    private String queryRequire;

    private String handleReason;

    private String attachment;

    private String submitTime;

    private String rescanCode;

    private String rescanResult;

    private String sponsor;

    private String checkStatus;

    private String checkOpinion;

    private String checker;

    private String checkTime;
}
