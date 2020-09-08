package com.kyc.mapper.customer;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QueryMapper {
    Map<String, Integer> customerQueryCount();

    List<Map<String, Object>> listCustomerQueryInfo(Map<String, Object> map);

    Integer addCustomerQueryInfo(Map<String, Object> map);

    List<Map<String, Object>> selectQueryList(@Param("queryCode") String queryCode);

    Integer handleQueryExcept(@Param("except") Map<String, Object> queryMap);
}
