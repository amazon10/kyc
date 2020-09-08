package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScanList implements Serializable {
    private static final long serialVersionUID = -8597710984075315489L;

    private Integer scanListId;

    private String scanMain;

    private String scanRequire;

    private String scanStatus;

    private String scanResult;

    private String scanRelation;

    private String scanIdNum;

    private String scanHitReason;

    private String templateCode;
}
