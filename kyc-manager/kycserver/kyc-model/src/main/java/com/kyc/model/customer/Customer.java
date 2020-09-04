package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;
import java.sql.Blob;

@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = -1439536632483411803L;

    private Integer customerId;

    private String customerCode;

    private String customerName;

    private String industry;

    private String area;

    private String customerType;

    private String customerLevel;

    private String recogStatus;

    private String channel;

    private String createTime;

    private String updateTime;

    private Boolean pushStatus;

    private Blob uploadFile;

    private String customerStatus;

    private String customerMode;
}
