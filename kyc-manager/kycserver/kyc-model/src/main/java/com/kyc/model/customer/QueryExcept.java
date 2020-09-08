package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryExcept implements Serializable {
    private static final long serialVersionUID = -925867252695720867L;

    private Integer id;

    private String queryCode;

    private String exceptHandle;

    private String queryRequire;

    private String handleReason;

    private String attachment;

    private String submitTime;

    private String requeryCode;

    private String requeryResult;

    private String sponsor;
}
