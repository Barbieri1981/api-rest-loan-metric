package com.example.restservice.exception.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfoDTO {
    private String code;
    private String message;
    private String errorDescription;
}
