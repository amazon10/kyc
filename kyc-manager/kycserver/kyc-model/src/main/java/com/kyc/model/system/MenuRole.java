package com.kyc.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuRole implements Serializable {

    private static final long serialVersionUID = -818914152057582521L;

    private String id;

    private String roleId;

    private String menuId;
}
