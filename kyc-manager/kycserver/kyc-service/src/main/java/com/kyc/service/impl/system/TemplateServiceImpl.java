package com.kyc.service.impl.system;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.kyc.mapper.system.TemplateMapper;
import com.kyc.model.VariableConstants;
import com.kyc.model.system.Template;
import com.kyc.service.system.TemplateService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    TemplateMapper templateMapper;

    @Override
    public List<Template> listTemplates(Template template, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return templateMapper.listTemplates(template);
    }

    @Override
    public Integer addTemplates(List<Map<String, Object>> templateList) {

        List<Template> templates = new ArrayList<>();
        List<Template> globalTemplates = new ArrayList<>();
        templateList.stream().forEach(item -> {
            Template template = BeanUtil.mapToBean(item, Template.class, false, CopyOptions.create());
            templates.add(template);
        });

        Integer result = templateMapper.addTemplates(templates);
        if (result <= 0) {
            log.warning("add template information failed...");
            return -1;
        }

        return result;
    }

    @Override
    public Boolean templateStatus(Boolean status) {
        /*DateTimeFormatter dateTimeFormattera = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();*/

        Boolean result = true;
        if (status) {
            result = templateMapper.templateStatus("1", null, DateUtil.format(LocalDateTime.now(), VariableConstants.DATETIME_FORMATTER));
        } else {
            result = templateMapper.templateStatus("0", DateUtil.format(LocalDateTime.now(), VariableConstants.DATETIME_FORMATTER), null);
        }

        return result;
    }

}
