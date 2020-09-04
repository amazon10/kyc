package com.kyc.mapper.system;

import com.kyc.model.system.Template;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemplateMapper {

    List<Template> listTemplates(@Param("template") Template template);

    Integer addTemplates(@Param("list") List<Template> templates);

    Boolean templateStatus(@Param("status") String status, @Param("enableTime") String enableTime,
                                 @Param("disableTime") String disableTime);

}
