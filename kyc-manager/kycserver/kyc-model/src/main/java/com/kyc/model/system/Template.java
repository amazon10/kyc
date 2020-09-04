package com.kyc.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class Template implements Serializable {
    private static final long serialVersionUID = 4193104167552926460L;

    private Integer id;

    private String channel;

    private String templateCode;

    private String templateName;

    private String createTime;

    private String createUser;

    private String templateStatus;

    private String enableTime;

    private String disableTime;

    private String templateMode;

    private String idLegalPerson;

    private String idRespPerson;

    private String idAssInd;

    private String idAssOther;

    private String deepQuery;

    private String assPenetrate;

    private String recogPipeline;

    private String scanComMain;

    private String scanComMain1;

    private String scanLegalRep;

    private String scanRespPerson;

    private String scanAssInd;

    private String scanAssOther;

    private String scanAssCompany;

    private String requireScene;

    private String idInfoVerify;

    private String idAspirationVerify;

    private String scanWhiteList;

    private String scanBlackList;
}
