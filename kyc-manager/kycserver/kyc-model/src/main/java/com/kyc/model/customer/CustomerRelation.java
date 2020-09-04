package com.kyc.model.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerRelation implements Serializable {
    private static final long serialVersionUID = -1072817112591596351L;

    private Integer id;

    private Integer customerId;

    private Integer customerRelationId;

    private Integer customerInfoId;

    private String relation;
}
