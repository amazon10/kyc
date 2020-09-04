package com.kyc.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 5470014979719495564L;

    private Integer id;

    private String msgDesc;

    private String msgSource;

    private String msgSourceId;

    private String pushTime;

    private String customerCode;

    private String msgLevel;

    private String processStatus;

    private String consltStatus;

    private String startTime;

    private String endTime;
}
