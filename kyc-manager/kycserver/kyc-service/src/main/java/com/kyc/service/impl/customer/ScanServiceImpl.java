package com.kyc.service.impl.customer;

import com.github.pagehelper.PageHelper;
import com.kyc.mapper.customer.ScanMapper;
import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.Scan;
import com.kyc.model.customer.ScanExcept;
import com.kyc.model.customer.ScanList;
import com.kyc.service.customer.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScanServiceImpl implements ScanService {

    @Autowired
    ScanMapper scanMapper;

    @Override
    public List<Scan> listScanInfo(Map<String, Object> scanMap, Integer pageNo, Integer psgeSize) {
        PageHelper.startPage(pageNo, psgeSize);

        return scanMapper.listScanInfo(scanMap);
    }

    @Override
    public List<ScanList> listScanListInfo(String scanCode) {
        return scanMapper.listScanListInfo(scanCode);
    }

    @Override
    public Integer addScanInfo(Scan scan) {
        return scanMapper.addScanInfo(scan);
    }

    @Override
    public List<Map<String, Object>> listScanCheck(Map<String, Object> checkMap, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return scanMapper.listScanCheck(checkMap);
    }

    @Override
    public Integer batchUpdateScanStatus(Map<String, Object> map, List<String> scans) {
        return scanMapper.batchUpdateScanStatus(map, scans);
    }

    @Override
    public List<Map<String, Object>> listCustomerBasic(Scan scan) {
        return scanMapper.listCustomerBasic(scan);
    }

    @Override
    public List<ScanList> listScanLists(Scan scan) {
        return scanMapper.listScanLists(scan);
    }

    @Override
    public List<ScanExcept> listScanExcept(Scan scan) {
        return scanMapper.listScanExcept(scan);
    }
}
