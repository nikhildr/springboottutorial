package com.skyfall.tutorial.springbootapplication.controller;

import com.skyfall.tutorial.springbootapplication.dto.FileResponseDTO;
import com.skyfall.tutorial.springbootapplication.service.EmployeeService;
import com.skyfall.tutorial.springbootapplication.service.EmployeeServiceImpl;
import com.skyfall.tutorial.springbootapplication.service.FileSystemStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class EmployeeController {

    private static final String TEMP = "temp/";


    @Autowired
    private FileSystemStorage fileSystemStorage;
    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/uploadfile")
    public ResponseEntity<FileResponseDTO> uploadSingleFile(@RequestParam("file") MultipartFile file) {
        String upfile = fileSystemStorage.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(upfile)
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new FileResponseDTO(upfile, fileDownloadUri, "File uploaded with success!"));
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws FileNotFoundException {

        Resource resource = null;
        resource = fileSystemStorage.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} ,value = "/uploadFile")
    public @ResponseBody ResponseEntity uploadFile(
            @RequestParam(value = "files") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {

                employeeService.saveCars(file.getInputStream());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
