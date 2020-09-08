package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class Query implements Serializable {
    private static final long serialVersionUID = -1055923227229423480L;

    private Integer id;

    private String queryCode;

    private String customerCode;

    private String queryStatus;

    private String queryType;

    private String queryTime;

    private String processStatus;

    private String templateCode;
}
