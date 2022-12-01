package com.skyfall.tutorial.springbootapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponseDTO {
    private String fileName;
    private String fileUrl;
    private String message;
}
