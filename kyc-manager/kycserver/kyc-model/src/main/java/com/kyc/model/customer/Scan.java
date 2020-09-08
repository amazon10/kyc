package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class Scan implements Serializable {
    private static final long serialVersionUID = -5218545288046017342L;

    private Integer scanId;

    private String scanCode;

    private String customerCode;

    private String customerName;

    private String scanStatus;

    private String scanTime;

    private ScanList scanList;

    private String scanResult;

    private String processStatus;

    private String uscCode;

    private String idNum;

    private String handleReason;
}
