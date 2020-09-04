package com.kyc.service.system;

import com.kyc.model.system.Template;

import java.util.List;
import java.util.Map;

public interface TemplateService {

    List<Template> listTemplates(Template globalTemplate, Integer pageNo, Integer pageSize);

    Integer addTemplates(List<Map<String, Object>> templates);

    Boolean templateStatus(Boolean status);

}
