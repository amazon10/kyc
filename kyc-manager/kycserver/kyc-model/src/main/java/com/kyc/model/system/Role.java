package com.kyc.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 3282146039113883000L;

    private Integer id;

    private String name;

    private String namezh;

    private String desc;

    private String attr;

    private String updateTime;

    private List<Integer> menuIds;
}
