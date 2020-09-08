package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryList implements Serializable {
    private static final long serialVersionUID = 2563494749958892132L;

    private Integer id;

    private String queryCode;

    private String verifyMain;

    private String templateCode;

    private String queryRequire;

    private String queryDetail;

    private String queryStatus;
}
