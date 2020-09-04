package com.kyc.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 机构管理信息
 */
@Data
@TableName("kyc_org")
public class Org implements Serializable {

    private static final long serialVersionUID = 3255761271757729129L;

    private String id;

    private String orgCode;

    private String orgName;

    private String orgType;

    private String orgArea;

    private String orgAddress;

    private String updateTime;

    private String remark;
}
