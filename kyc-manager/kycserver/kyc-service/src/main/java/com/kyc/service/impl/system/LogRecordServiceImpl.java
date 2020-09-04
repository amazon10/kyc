package com.kyc.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.kyc.mapper.system.LogRecordMapper;
import com.kyc.model.system.LogRecord;
import com.kyc.service.system.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogRecordServiceImpl implements LogRecordService {

    @Autowired
    LogRecordMapper logRecordMapper;

    @Override
    public List<LogRecord> listLogRecords(LogRecord logRecord, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return logRecordMapper.listLogRecords(logRecord);
    }

    @Override
    public Integer addLogRecord(LogRecord logRecord) {
        return logRecordMapper.addLogRecord(logRecord);
    }

}
