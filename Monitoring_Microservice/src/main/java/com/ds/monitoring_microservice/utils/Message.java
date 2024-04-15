package com.ds.monitoring_microservice.utils;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private UUID id;
    private String message;
}

