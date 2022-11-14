package com.skyfall.tutorial.springbootapplication.service;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import com.skyfall.tutorial.springbootapplication.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository repository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Account")
                .departmentAddress("banglore")
                .departmentCode("CA")
                .departmentId(1l)
                .build();

        Mockito.when(repository.findByDepartmentCode("CA")).thenReturn(department);
    }

    @Test
    @DisplayName("Get data based on valid department code")
    public void findDepartmentByCode() {
        String departmentCode = "CA";
        Department found = departmentService.findDepartmentByCode(departmentCode);
        assertEquals(departmentCode, found.getDepartmentCode());

    }
}