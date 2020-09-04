package com.kyc.service.system;

import com.kyc.model.system.Dictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> listDictionaries(Dictionary dictionary, Integer pageNo, Integer pageSize);

    Boolean updateDicRemark(Integer id, String remark);
}
