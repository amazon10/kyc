package com.kyc.controller.system;

import com.kyc.model.RespBean;
import com.kyc.model.system.LogRecord;
import com.kyc.service.system.LogRecordService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Log
@Controller("/system")
public class LogRecordController {

    @Autowired
    LogRecordService logRecordService;

    @PostMapping("/log/info")
    public RespBean listLogRecords(@RequestBody LogRecord logRecord, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {
        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 0 ? 10 : pageSize;

        List<LogRecord> logRecordList = logRecordService.listLogRecords(logRecord, pageNo, pageSize);
        if (logRecordList == null) {
            log.warning("select log record information failed...");
            return RespBean.error("获取日志记录信息失败");
        }

        return RespBean.ok("获取日志记录信息成功", logRecordList);
    }

}
