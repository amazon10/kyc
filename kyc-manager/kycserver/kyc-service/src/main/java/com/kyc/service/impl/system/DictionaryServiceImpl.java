package com.kyc.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.kyc.mapper.system.DictionaryMapper;
import com.kyc.model.system.Dictionary;
import com.kyc.service.system.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> listDictionaries(Dictionary dictionary, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return dictionaryMapper.listDictionaries(dictionary);
    }

    @Override
    public Boolean updateDicRemark(Integer id, String remark) {
        return dictionaryMapper.updateDicRemark(id, remark);
    }
}
