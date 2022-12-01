package com.skyfall.tutorial.springbootapplication.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadDTO {
    private String location;


}
