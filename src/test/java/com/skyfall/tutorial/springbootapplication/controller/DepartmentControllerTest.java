package com.skyfall.tutorial.springbootapplication.controller;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import com.skyfall.tutorial.springbootapplication.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
         department = Department.builder()
                .departmentName("Bio technology")
                .departmentAddress("hyderabad")
                .departmentCode("BT")
                .departmentId(1l)
                .build();
    }

    @Test
    @DisplayName("when_valid_department_data_save_success")
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("Bio technology")
                .departmentAddress("hyderabad")
                .departmentCode("BT")
                .build();

        Mockito.when(departmentService.saveDepartment(department))
                .thenReturn(inputDepartment);
        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"departmentName\":\"account\",\n" +
                                "    \"departmentAddress\":\"banglore\",\n" +
                                "    \"departmentCode\":\"CA\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getDepartmentById() throws Exception {

        Mockito.when(departmentService.findById(1l)).thenReturn(department);
        mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));
    }
}