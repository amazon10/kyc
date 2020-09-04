package com.kyc.controller.system;

import com.kyc.model.RespBean;
import com.kyc.model.system.Message;
import com.kyc.service.system.MessageService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Log
@Controller("/system")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/message/list")
    public RespBean listMessages(@RequestBody Message message, @RequestBody Integer pageNo, @RequestBody Integer pageSize) {
        pageNo = pageNo < 0 ? 1 : pageNo;
        pageSize = pageSize < 0 ? 10 : pageSize;

        List<Message> messageList = messageService.listMessages(message, pageNo, pageSize);
        if (messageList == null) {
            log.warning("get message list failed...");
            return RespBean.error("获取消息失败");
        }

        return RespBean.ok("获取消息成功", messageList);
    }

    @PostMapping("/message/mark")
    public RespBean updateMark(@RequestBody Message message, @RequestBody List<Integer> msgIds) {
        Boolean result = messageService.updateMark(message, msgIds);
        if (!result) {
            log.warning("update message mark status failed...");
            return RespBean.error("消息标记失败");
        }

        return RespBean.ok("消息标记成功");
    }
}
