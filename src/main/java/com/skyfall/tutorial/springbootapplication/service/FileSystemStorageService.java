package com.skyfall.tutorial.springbootapplication.service;

import com.skyfall.tutorial.springbootapplication.dto.FileUploadDTO;
import com.skyfall.tutorial.springbootapplication.exception.FileStorageException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements FileSystemStorage {

    private final Path dirLocation;

    @Autowired
    public FileSystemStorageService(FileUploadDTO dto) {
        this.dirLocation = Paths.get(dto.getLocation()).toAbsolutePath().normalize();
    }

    @Override
    @PostConstruct
    public void init() {

        try {
            if(!dirLocation.toFile().exists())
                Files.createDirectory(dirLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String saveFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path dfile = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Could not upload file");
        }
    }

    @SneakyThrows
    @Override
    public Resource loadFile(String fileName)  {
        try {
            Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Could not find file");
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            throw new FileNotFoundException("Could not download file");
        }
    }
}
