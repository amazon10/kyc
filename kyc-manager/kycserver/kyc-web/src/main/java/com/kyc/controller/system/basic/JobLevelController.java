package com.kyc.controller.system.basic;

import com.kyc.model.JobLevel;
import com.kyc.model.RespBean;
import com.kyc.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-10-01 17:59
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;
    @GetMapping("/")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.addJobLevel(jobLevel) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateJobLevelById(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.updateJobLevelById(jobLevel) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id) {
        if (jobLevelService.deleteJobLevelById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @DeleteMapping("/")
    public RespBean deleteJobLevelsByIds(Integer[] ids) {
        if (jobLevelService.deleteJobLevelsByIds(ids) == ids.length) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}