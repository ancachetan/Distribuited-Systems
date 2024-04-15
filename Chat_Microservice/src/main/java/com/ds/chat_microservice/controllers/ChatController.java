package com.ds.chat_microservice.controllers;

import com.ds.chat_microservice.dtos.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/chat")
public interface ChatController {
    @PostMapping("/sendAndSave/admin")
    ResponseEntity<MessageDto> sendAndSaveAdminMessage(@RequestHeader("Authorization") String token, @RequestBody MessageDto messageDTO);

    @PostMapping("/sendAndSave/client")
    ResponseEntity<MessageDto> sendAndSaveClientMessage(@RequestHeader("Authorization") String token, @RequestBody MessageDto messageDTO);

    @GetMapping("/getMessages/{id1}/{id2}")
    ResponseEntity<List<MessageDto>> getMessagesFromAConversation(@RequestHeader("Authorization") String token, @PathVariable UUID id1, @PathVariable UUID id2);

    @PostMapping("/update")
    ResponseEntity<MessageDto> updateSeenStatus(@RequestHeader("Authorization") String token, @RequestBody MessageDto message);
}

