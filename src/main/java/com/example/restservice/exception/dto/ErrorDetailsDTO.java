package com.example.restservice.exception.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsDTO {
    private ErrorInfoDTO error;
}
