package com.ds.chat_microservice.services;

import com.ds.chat_microservice.dtos.MessageDto;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    void sendToAdmin(MessageDto message);

    void sendToClient(MessageDto message);

    MessageDto getById(UUID id);

    List<MessageDto> getByToAndFrom(UUID to, UUID from);

    MessageDto save(MessageDto message);

    MessageDto updateSeenStatus(MessageDto message);
}
