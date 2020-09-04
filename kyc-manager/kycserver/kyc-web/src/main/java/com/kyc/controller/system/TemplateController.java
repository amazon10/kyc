package com.kyc.controller.system;

import cn.hutool.core.util.StrUtil;
import com.kyc.model.RespBean;
import com.kyc.model.system.Template;
import com.kyc.service.system.TemplateService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Log
@Controller("/system")
public class TemplateController {

    @Autowired
    TemplateService templateService;

    @PostMapping("/template/list")
    public RespBean listGlobalTemplates(@RequestBody Template template, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {

        pageNo = pageNo <= 0 ? 1 : pageNo;
        pageSize = pageSize <= 0 ? 10 : pageSize;

        if (StrUtil.isEmpty(template.getTemplateMode())) {
            log.warning("can not detect the template mode, so can not list templates......");
            return RespBean.error("没有提供模板模式");
        }

        List<Template> templateList = templateService.listTemplates(template, pageNo, pageSize);

        return RespBean.ok("获取模板列表成功", templateList);
    }

    @PostMapping("/template/info")
    public RespBean addGlobalTemplates(@RequestBody List<Map<String, Object>> templates) {
        Integer result = templateService.addTemplates(templates);
        if (result <= 0) {
            log.warning("add template information failed...");
            return RespBean.error("模板信息添加失败");
        }

        return RespBean.ok("模板信息添加成功");
    }

    @PostMapping("/template/status")
    public RespBean globalTemplateStatus(@RequestBody Boolean status) {

        Boolean result = templateService.templateStatus(status);

        if (status && !result) {
            log.warning("global template status stop failed...");
            return RespBean.error("停用模板失败");
        } else if (status && result) {
            log.info("global template status top success...");
            return RespBean.ok("停用模板成功");
        } else if (!status && !result) {
            log.warning("global template status start failed...");
            return RespBean.error("启用模板失败");
        } else if (!status && result) {
            log.info("global template status start success...");
            return RespBean.ok("启用模板成功");
        }

        return RespBean.error("模板状态更新失败");
    }
}
