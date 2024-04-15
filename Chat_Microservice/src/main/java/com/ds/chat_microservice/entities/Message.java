package com.ds.chat_microservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message_data")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "from_user", nullable = false)
    private UUID from;
    @Column(name = "to_user", nullable = false)
    private UUID to;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "seen", nullable = false)
    private boolean seen;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime localDateTime;
}
