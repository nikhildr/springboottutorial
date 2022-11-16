package com.skyfall.tutorial.springbootapplication.controller;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import com.skyfall.tutorial.springbootapplication.exception.DepartmentNotFoundException;
import com.skyfall.tutorial.springbootapplication.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
