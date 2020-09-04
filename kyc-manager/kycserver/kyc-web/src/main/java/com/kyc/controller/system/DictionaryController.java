package com.kyc.controller.system;

import com.kyc.model.RespBean;
import com.kyc.model.system.Dictionary;
import com.kyc.service.system.DictionaryService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Log
@Controller("/system")
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;

    @PostMapping("/dic/info")
    public RespBean listDictionaries(@RequestBody Dictionary dictionary, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {

        pageNo = pageNo < 0 ? 1 : pageNo;
        pageSize = pageSize < 0 ? 10 : pageSize;

        List<Dictionary> dictionaryList = dictionaryService.listDictionaries(dictionary, pageNo, pageSize);
        if (dictionaryList == null) {
            log.warning("list dictionaries failed...");
            return RespBean.error("获取数据字典信息失败");
        }

        return RespBean.ok("获取数据字典信息成功", dictionaryList);
    }

    @PostMapping("/dic/remark")
    public RespBean updateDicRemark(@RequestBody Integer id, @RequestBody String remark) {
        Boolean result = dictionaryService.updateDicRemark(id, remark);
        if (!result) {
            log.warning("update dictionary remark information failed...");
            return RespBean.error("更新数据字典备注失败");
        }

        return RespBean.ok("更新数据字典备注成功");
    }

}
