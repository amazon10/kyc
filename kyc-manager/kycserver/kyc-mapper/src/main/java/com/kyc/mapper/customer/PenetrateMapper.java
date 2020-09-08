package com.kyc.mapper.customer;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PenetrateMapper {
    Map<String, Integer> customerpenetrateCount();

    List<Map<String, Object>> listCustomerPenetrateInfo(@Param("map") Map<String, Object> penetrateMap);

    Integer addCustomerPenetrateInfo(@Param("list") List<Map<String, Object>> map);
}
