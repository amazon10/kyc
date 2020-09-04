package com.kyc.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 2170185145251217665L;

    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Integer parentId;

    private Boolean enabled;

    private List<Role> roleList;
}
