package com.kyc.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dictionary implements Serializable {
    private static final long serialVersionUID = -7622350329670324000L;

    private Integer id;

    private String field;

    private String fieldName;

    private String fieldType;

    private String fieldSource;

    private Integer dataLen;

    private String primaryKey;

    private String remark;
}
