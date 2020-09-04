package com.kyc.mapper.system;

import com.kyc.model.system.Message;

import java.util.List;

public interface MessageMapper {
    List<Message> listMessages(Message message);

    Boolean updateMark(Message message, List<Integer> msgIds);
}
