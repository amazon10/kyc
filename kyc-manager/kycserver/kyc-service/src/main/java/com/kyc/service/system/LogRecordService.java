package com.kyc.service.system;

import com.kyc.model.system.LogRecord;

import java.util.List;

public interface LogRecordService {

    List<LogRecord> listLogRecords(LogRecord logRecord, Integer pageNo, Integer pageSize);

    Integer addLogRecord(LogRecord logRecord);
}
