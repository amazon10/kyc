package com.kyc.service.customer;

import cn.hutool.json.JSONObject;
import com.kyc.model.customer.*;

import java.util.List;
import java.util.Map;

public interface VerifyService {

    List<VerifyRelation> listVerifyRelations(VerifyRelation verifyRelation, Integer pageNo, Integer pageSize);

    Map<String, Integer> customerVerifyCount(String verifyMode);

    Integer addVerifyInfo(List<VerifyInfo> verifyInfo);

    Map<String, List<CustomerInfo>> getCustomerInfo(String customerCode);

    List<VerifyResult> getVerifyResultByVerifyCode(String verifyCode);

    List<VerifyExcept> getVerifyExceptByVerifyCode(String verifyCode);

    Integer submitVerify(VerifyCheck verifyCheck);

    List<Map<String, Object>> listVerifyExcept(JSONObject jsonObject);

    Integer checkStatus(List<Integer> ids, VerifyCheck verifyCheck);
}
