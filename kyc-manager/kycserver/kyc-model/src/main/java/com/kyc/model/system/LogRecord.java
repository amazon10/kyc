package com.kyc.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogRecord implements Serializable {
    private static final long serialVersionUID = 6510071399961870772L;

    private Integer id;

    private String username;

    private String loginId;

    private String loginTime;

    private String startTime;

    private String endTime;

    private String logContent;
}
