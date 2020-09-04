package com.kyc.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.kyc.mapper.system.MessageMapper;
import com.kyc.model.system.Message;
import com.kyc.service.system.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> listMessages(Message message, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        return messageMapper.listMessages(message);
    }

    @Override
    public Boolean updateMark(Message message, List<Integer> msgIds) {

        return messageMapper.updateMark(message, msgIds);
    }

}
