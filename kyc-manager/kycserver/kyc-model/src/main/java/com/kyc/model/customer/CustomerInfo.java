package com.kyc.model.customer;

import jdk.nashorn.internal.ir.Block;
import lombok.Data;

import java.io.Serializable;
import java.sql.Blob;

@Data
public class CustomerInfo implements Serializable {
    private static final long serialVersionUID = -5666859430235575198L;

    private Integer customerInfoId;

    private Blob bussinessLicense;

    private String uscCode;

    private String startTime;

    private String endTime;

    private Boolean longTerm;

    private String province;

    private String city;

    private String district;

    private String address;

    private String idType;

    private String idNum;

    private Blob idFront;

    private Block idBack;

    private String telNum;

    private Boolean sex;

    private String remark;

    private Blob attachment;
}
