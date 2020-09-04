package com.kyc.model.system;

import java.io.Serializable;

public class UserRole implements Serializable {

    private static final long serialVersionUID = -7741029849758574576L;

    private Integer id;

    private Integer userId;

    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
