package com.ds.chat_microservice.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDto {
    private UUID id;
    private UUID from;
    private UUID to;
    private String content;
    private boolean seen;
    private LocalDateTime localDateTime;
}
