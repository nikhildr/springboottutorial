package com.skyfall.tutorial.springbootapplication.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;


public interface FileSystemStorage {

    void init();

    String saveFile(MultipartFile file);

    Resource loadFile(String fileName) throws FileNotFoundException;
}
