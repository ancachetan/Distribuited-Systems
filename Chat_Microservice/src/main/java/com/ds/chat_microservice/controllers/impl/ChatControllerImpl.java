package com.ds.chat_microservice.controllers.impl;

import com.ds.chat_microservice.controllers.ChatController;
import com.ds.chat_microservice.dtos.MessageDto;
import com.ds.chat_microservice.security.JwtService;
import com.ds.chat_microservice.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatControllerImpl implements ChatController {
    private final MessageService messageService;
    private final JwtService jwtService;

    public ChatControllerImpl(MessageService messageService, JwtService jwtService) {
        this.messageService = messageService;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<MessageDto> sendAndSaveAdminMessage(String token, MessageDto messageDTO) {
        if (this.jwtService.authorizeByAdminRole(token)) {
            MessageDto savedMessage = messageService.save(messageDTO);
            messageService.sendToClient(savedMessage);
            return new ResponseEntity<>(savedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<MessageDto> sendAndSaveClientMessage(String token, MessageDto messageDTO) {
        if (this.jwtService.authorizeByClientRole(token)) {
            MessageDto savedMessage = messageService.save(messageDTO);
            messageService.sendToAdmin(savedMessage);
            return new ResponseEntity<>(savedMessage, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<MessageDto>> getMessagesFromAConversation(String token, @PathVariable UUID id1, @PathVariable UUID id2) {
        if (this.jwtService.authorizeBasedById(token, id1) || this.jwtService.authorizeBasedById(token, id2)) {
            return new ResponseEntity<>(messageService.getByToAndFrom(id1, id2), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<MessageDto> updateSeenStatus(String token, MessageDto message) {
        if (this.jwtService.authorizeByAdminOrClientRole(token)) {
            return new ResponseEntity<>(messageService.updateSeenStatus(message), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}
