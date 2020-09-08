package com.kyc.service.customer;

import com.kyc.model.customer.Scan;
import com.kyc.model.customer.ScanExcept;
import com.kyc.model.customer.ScanList;

import java.util.List;
import java.util.Map;

public interface ScanService {
    List<Scan> listScanInfo(Map<String, Object> scanMap, Integer pageNo, Integer psgeSize);

    List<ScanList> listScanListInfo(String scanCode);

    Integer addScanInfo(Scan scan);

    List<Map<String, Object>> listScanCheck(Map<String, Object> checkMap, Integer pageNo, Integer pageSize);

    Integer batchUpdateScanStatus(Map<String, Object> map, List<String> scans);

    List<Map<String, Object>>listCustomerBasic(Scan scan);

    List<ScanList> listScanLists(Scan scan);

    List<ScanExcept> listScanExcept(Scan scan);

}
