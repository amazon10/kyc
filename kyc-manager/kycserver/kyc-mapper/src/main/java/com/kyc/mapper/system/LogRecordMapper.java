package com.kyc.mapper.system;

import com.kyc.model.system.LogRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogRecordMapper {
    List<LogRecord> listLogRecords(@Param("log") LogRecord logRecord);

    Integer addLogRecord(@Param("log") LogRecord logRecord);
}
