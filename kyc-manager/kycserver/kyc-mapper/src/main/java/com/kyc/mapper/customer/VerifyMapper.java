package com.kyc.mapper.customer;

import com.kyc.model.customer.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VerifyMapper {
    List<VerifyRelation> listVerifyRelations(@Param("verify") VerifyRelation verifyRelation);

    Map<String, Integer> customerVerifyCount(@Param("mode") String verifyMode);

    Integer addVerifyInfo(@Param("verifys") List<VerifyInfo> verifyInfos);

    List<VerifyResult> getVerifyResultByVerifyCode(@Param("verifyCode") String verifyCode);

    List<VerifyExcept> getVerifyExceptByVerifyCode(@Param("verifyCode") String verifyCode);

    Integer submitVerify(@Param("verify") VerifyCheck verifyCheck);

    List<Map<String, Object>> listVerifyExcept(@Param("map") Map<String, Object> map);

    Integer checkStatus(@Param("ids") List<Integer> ids, @Param("check") VerifyCheck verifyCheck);

    VerifyInfo selectVerifyInfo(@Param("customerCode") String customerCode);
}
