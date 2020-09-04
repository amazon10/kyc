package com.kyc.mapper.system;

import com.kyc.model.system.Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictionaryMapper {

    List<Dictionary> listDictionaries(@Param("dic") Dictionary dictionary);

    Boolean updateDicRemark(@Param("id") Integer id, @Param("remark") String remark);
}
