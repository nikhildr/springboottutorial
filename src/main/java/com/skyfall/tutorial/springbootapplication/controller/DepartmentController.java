package com.skyfall.tutorial.springbootapplication.controller;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import com.skyfall.tutorial.springbootapplication.exception.DepartmentNotFoundException;
import com.skyfall.tutorial.springbootapplication.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        log.info("inside save department");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartment(@PathVariable long id) {
        boolean status = departmentService.deleteDepartment(id);
        return status ? "department deleted :)" : "not deleted";
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Department.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable long id) throws DepartmentNotFoundException {
        Department department = departmentService.findById(id);
        return department;
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department getDepartmentByCode(@PathVariable String name) {
        return departmentService.findDepartmentByCode(name);
    }

    @GetMapping("/departmentsByPage")
    public List<Department> filterBooks(@ParameterObject Pageable pageable) {
        return departmentService.getAllDepartmentsByPage(pageable);
    }
}
