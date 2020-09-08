package com.kyc.controller.customer;

import cn.hutool.json.JSONObject;
import com.kyc.model.RespBean;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.Scan;
import com.kyc.model.customer.ScanExcept;
import com.kyc.model.customer.ScanList;
import com.kyc.service.customer.ScanService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Log
@Controller("/customer")
public class ScanController {
    @Autowired
    ScanService scanService;

    @PostMapping("/scan/info")
    public RespBean listScanInfo(@RequestBody Map<String, Object> scanMap, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {
        List<Scan> result = scanService.listScanInfo(scanMap, pageNo, pageSize);
        if (result == null) {
            log.warning("list scan information failed...");
            return RespBean.error("获取筛查信息失败");
        }

        return RespBean.ok("获取筛查信息成功", result);
    }

    @PostMapping("/scan/list/info")
    public RespBean listScanListInfo(@RequestBody String scanCode) {
        List<ScanList> scanLists = scanService.listScanListInfo(scanCode);
        if (scanLists == null) {
            log.warning("list scan list information failed...");
            return RespBean.error("获取筛查清单信息失败");
        }

        return RespBean.ok("获取筛查清单信息成功", scanLists);
    }

    @PostMapping("/scan/info/add")
    public RespBean addScanInfo(@RequestBody Scan scan) {
        Integer result = scanService.addScanInfo(scan);
        if (result <= 0) {
            log.warning("add scan information failed...");
            return RespBean.error("筛查信息添加失败");
        }

        return RespBean.ok("筛查信息添加成功");
    }

    @PostMapping("/scan/check")
    public RespBean listScanCheck(@RequestBody Map<String, Object> checkMap, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {
        List<Map<String, Object>> result = scanService.listScanCheck(checkMap, pageNo, pageSize);
        if (result == null) {
            log.warning("list scan check information failed...");
            return RespBean.error("获取筛查调整审核信息失败");
        }

        return RespBean.ok("获取筛查调整审核信息成功", result);
    }

    @PostMapping("/scan/check/status")
    public RespBean batchUpdateScanStatus(@RequestBody Map<String, Object> map, List<String> scanCode) {
        Integer result = scanService.batchUpdateScanStatus(map, scanCode);
        if (result <= 0) {
            log.warning("update scan check status failed...");
            return RespBean.error("审核筛查信息失败");
        }

        return RespBean.ok("审核筛查信息成功");
    }

    @PostMapping("/scan/check/detail")
    public RespBean listScanCheckDetail(@RequestBody Scan scan) {
        List<Map<String, Object>> customerInfos = scanService.listCustomerBasic(scan);
        if (customerInfos == null) {
            log.warning("fetch customer basic information failed...");
            return RespBean.error("获取客户基本信息失败");
        }

        List<ScanList> scanLists = scanService.listScanLists(scan);
        if (scanLists == null) {
            log.warning("fetch scan result information failed...");
            return RespBean.error("获取筛查结果信息失败");
        }

        List<ScanExcept> scanExcepts = scanService.listScanExcept(scan);
        if (scanExcepts == null) {
            log.warning("fetch scan except information failed...");
            return RespBean.error("获取异常处理信息失败");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("basic", customerInfos);
        jsonObject.set("result", scanLists);
        jsonObject.set("except", scanExcepts);

        return RespBean.ok("获取调整审核信息成功", jsonObject);
    }
}
