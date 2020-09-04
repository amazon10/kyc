package com.kyc.service.system;

import com.kyc.model.system.Message;

import java.util.List;

public interface MessageService {

    List<Message> listMessages(Message message, Integer pageNo, Integer pageSize);

    Boolean updateMark(Message message, List<Integer> msgIds);
}
