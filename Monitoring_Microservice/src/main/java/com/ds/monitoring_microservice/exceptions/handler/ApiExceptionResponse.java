package com.ds.monitoring_microservice.exceptions.handler;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionResponse {
    private LocalDateTime currentTime;
    private String message;
    private int status;
    private Map<String, String> errors;
}
