package com.kyc.service.impl.customer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.kyc.mapper.customer.CustomerMapper;
import com.kyc.mapper.customer.VerifyMapper;
import com.kyc.model.customer.*;
import com.kyc.service.customer.VerifyService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    VerifyMapper verifyMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<VerifyRelation> listVerifyRelations(VerifyRelation verifyRelation, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return verifyMapper.listVerifyRelations(verifyRelation);
    }

    @Override
    public Map<String, Integer> customerVerifyCount(String verifyMode) {

        return verifyMapper.customerVerifyCount(verifyMode);
    }

    @Override
    public Integer addVerifyInfo(List<VerifyInfo> verifyInfo) {
        return verifyMapper.addVerifyInfo(verifyInfo);
    }

    @Override
    public Map<String, List<CustomerInfo>> getCustomerInfo(String customerCode) {
        List<CustomerInfo> customerInfos = customerMapper.getCustomerInfoByCustomerCode(customerCode);
        if (CollUtil.isEmpty(customerInfos)) {
            log.warning("fetch customer information by customer code failed...");
            return null;
        }

        Map<String, List<CustomerInfo>> customerInfoMap = new HashMap<>();
        customerInfoMap.put(customerCode, customerInfos);

        return customerInfoMap;
    }

    @Override
    public List<VerifyResult> getVerifyResultByVerifyCode(String verifyCode) {
        return verifyMapper.getVerifyResultByVerifyCode(verifyCode);
    }

    @Override
    public List<VerifyExcept> getVerifyExceptByVerifyCode(String verifyCode) {
        return verifyMapper.getVerifyExceptByVerifyCode(verifyCode);
    }

    @Override
    public Integer submitVerify(VerifyCheck verifyCheck) {
        return verifyMapper.submitVerify(verifyCheck);
    }

    @Override
    public List<Map<String, Object>> listVerifyExcept(JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>(jsonObject.size());
        jsonObject.entrySet().stream().forEach(item -> {
            map.put(item.getKey(), item.getValue());
        });
        return verifyMapper.listVerifyExcept(map);
    }

    @Override
    public Integer checkStatus(List<Integer>ids, VerifyCheck verifyCheck) {
        return verifyMapper.checkStatus(ids, verifyCheck);
    }
}
