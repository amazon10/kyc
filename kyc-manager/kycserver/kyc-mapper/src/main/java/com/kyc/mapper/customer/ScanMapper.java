package com.kyc.mapper.customer;

import com.kyc.model.customer.CustomerInfo;
import com.kyc.model.customer.Scan;
import com.kyc.model.customer.ScanExcept;
import com.kyc.model.customer.ScanList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScanMapper {

    List<Scan> listScanInfo(@Param("scanMap") Map<String, Object> scanMap);

    List<ScanList> listScanListInfo(@Param("scanCode") String scanCode);

    Integer addScanInfo(@Param("scan") Scan scan);

    List<Map<String, Object>> listScanCheck(@Param("map") Map<String, Object> checkMap);

    Integer batchUpdateScanStatus(@Param("map") Map<String, Object> map, @Param("scans") List<String> scans);

    List<Map<String, Object>> listCustomerBasic(@Param("scan") Scan scan);

    List<ScanList> listScanLists(@Param("scan") Scan scan);

    List<ScanExcept> listScanExcept(@Param("scan") Scan scan);
}
