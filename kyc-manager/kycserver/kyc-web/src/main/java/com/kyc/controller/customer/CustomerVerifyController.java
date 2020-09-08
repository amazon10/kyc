package com.kyc.controller.customer;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kyc.model.RespBean;
import com.kyc.model.customer.*;
import com.kyc.service.customer.VerifyService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Controller("/customer")
public class CustomerVerifyController {
    @Autowired
    VerifyService verifyService;

    @PostMapping("/verify/list")
    public RespBean listVerifyInfos(@RequestBody VerifyRelation cvr, @RequestBody Integer pageNo, @RequestBody Integer pageSizes) {
        List<VerifyRelation> verifyRelationList = verifyService.listVerifyRelations(cvr, pageNo, pageSizes);
        if (CollUtil.isEmpty(verifyRelationList)) {
            log.warning("list customer verify inforation faield...");
            return RespBean.error("获取客户核验信息失败");
        }

        log.info("list customer verify information success...");
        return RespBean.ok("获取客户核验信息成功", verifyRelationList);
    }

    @PostMapping("/verify/count")
        public RespBean customerVerifyCountQ12A(@RequestBody String verifyMode) {
        Map<String, Integer> verifyMap = verifyService.customerVerifyCount(verifyMode);
        if (CollUtil.isEmpty(verifyMap)) {
            log.warning("statistics customer verify failed....");
            return RespBean.error("统计客户核验数量失败");
        }

        return RespBean.ok("统计客户核验数量成功", verifyMap);
    }

    @PostMapping("/verify/info")
    public RespBean addVerifyInfo(@RequestBody List<Map<String, Object>> verifyInfos) {

        List<VerifyInfo> verifyInfoList = new ArrayList<>();
        verifyInfos.stream().forEach(item -> {
            VerifyInfo verifyInfo = BeanUtil.mapToBean(item, VerifyInfo.class, false, CopyOptions.create());
            verifyInfoList.add(verifyInfo);
        });

        Integer result = verifyService.addVerifyInfo(verifyInfoList);
        if (result <= 0) {
            log.warning("add verify information failed...");
            return RespBean.error("添加客户核验信息失败");
        }

        return RespBean.ok("添加客户核验信息成功");
    }

    @PostMapping("/verify/check")
    public RespBean submitVerify(@RequestBody VerifyCheck verifyCheck) {
        Integer result = verifyService.submitVerify(verifyCheck);
        if (result <= 0) {
            log.warning("submit verify check failed...");
            return RespBean.error("异常处理提交失败");
        }

        return RespBean.ok("异常处理提交成功");
    }

    @PostMapping("/verify/customer")
    public RespBean getVerifyReportByCompany(@RequestBody String customerCode, @RequestBody String verifyCode) {
        /* 获取客户相关信息 */
        Map<String, List<CustomerInfo>> customerInfo = verifyService.getCustomerInfo(customerCode);
        if (CollUtil.isEmpty(customerInfo)) {
            log.warning("get cutomer information failed...");
            return RespBean.error("根据客户编号获取客户详细信息失败");
        }

        /* 获取核验结果信息 */
        List<VerifyResult> verifyResults = verifyService.getVerifyResultByVerifyCode(verifyCode);
        if (CollUtil.isEmpty(verifyResults)) {
            log.warning("get verify result by verify code failed...");
            return RespBean.error("根据核验编号获取核验结果信息失败");
        }

        /* 获取异常处理信息 */
        List<VerifyExcept> verifyExcepts = verifyService.getVerifyExceptByVerifyCode(verifyCode);
        if (CollUtil.isEmpty(verifyExcepts)) {
            log.warning("get verify except by verify code failed...");
            return RespBean.error("根据核验编号获取异常处理结果信息失败");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("basic", customerInfo);
        jsonObject.set("result", verifyResults);
        jsonObject.set("except", verifyExcepts);

        return RespBean.ok("获取核验信息成功", jsonObject);
    }

    /**
     * 检索条件格式
     * {
     *    {
     *         "verifyCode": "",
     *         "customerCode": "",
     *         "customerName": "',
     *         "checkStatus*: "",
     *         "idNum": "",
     *         "customerMode": ""
     *     }
     * }
     * @param retrieve
     * @return
     */
    @PostMapping("/verify/except")
    public RespBean listVerifyExcept(@RequestBody String retrieve) {
        if (!JSONUtil.isJsonObj(retrieve)) {
            log.warning("search condition is not json format, so can not process it...");
            return RespBean.error("检索条件格式错误");
        }

        JSONObject jsonObject = JSONUtil.parseObj(retrieve);
        List<Map<String, Object>> result = verifyService.listVerifyExcept(jsonObject);
        if (result == null) {
            log.warning("list verify exception information failed...");
            return RespBean.error("获取异常处理核验信息失败");
        }

        return RespBean.ok("获取异常处理核验信息成功", result);
    }

    @PostMapping("/verify/check/status")
    public RespBean checkStatus(@RequestBody List<Integer> ids, @RequestBody VerifyCheck verifyCheck) {
        Integer result = verifyService.checkStatus(ids, verifyCheck);
        if (result <= 0) {
            log.warning("审核程序失败");
            return RespBean.error("审核通过或拒绝失败");
        }

        return RespBean.ok("审核通过或拒绝失败");
    }
}
