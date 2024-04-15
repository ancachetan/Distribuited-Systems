package com.ds.chat_microservice.services.impl;

import com.ds.chat_microservice.dtos.MessageDto;
import com.ds.chat_microservice.entities.Message;
import com.ds.chat_microservice.exceptions.MessageNotFoundException;
import com.ds.chat_microservice.repositories.MessageRepository;
import com.ds.chat_microservice.services.MessageService;
import com.ds.chat_microservice.utils.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final SimpMessagingTemplate template;

    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper, SimpMessagingTemplate template) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.template = template;
    }

    @Override
    public void sendToAdmin(MessageDto message) {
        template.convertAndSend("/topic/admin/" + message.getTo(), message);
    }

    @Override
    public void sendToClient(MessageDto message) {
        template.convertAndSend("/topic/client/" + message.getTo(), message);
    }

    @Override
    public MessageDto getById(UUID id) {
        return messageRepository.findById(id)
                .map(user -> modelMapper.map(user, MessageDto.class))
                .orElseThrow(() -> new MessageNotFoundException(ErrorMessages.MESSAGE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(id)));
    }

    @Override
    public List<MessageDto> getByToAndFrom(UUID to, UUID from) {
        List<MessageDto> messageList = messageRepository.findByToAndFromOrFromAndTo(to, from)
                .stream()
                .map(user -> modelMapper.map(user, MessageDto.class))
                .sorted(Comparator.comparing(MessageDto::getLocalDateTime))
                .toList();
        return messageList;
    }

    @Override
    public MessageDto save(MessageDto messageDto) {
        Message message = modelMapper.map(messageDto, Message.class);
        message.setLocalDateTime(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);
        return modelMapper.map(savedMessage, MessageDto.class);
    }

    @Override
    public MessageDto updateSeenStatus(MessageDto message) {
        Message foundMessage = messageRepository.findById(message.getId())
                .map(user -> modelMapper.map(user, Message.class))
                .orElseThrow(() -> new MessageNotFoundException(ErrorMessages.MESSAGE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(message.getId())));
        foundMessage.setSeen(message.isSeen());
        Message updatedMessage = messageRepository.save(foundMessage);
        return modelMapper.map(updatedMessage, MessageDto.class);
    }
}
