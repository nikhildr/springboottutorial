package com.skyfall.tutorial.springbootapplication.repository;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Electronica communication")
                .departmentAddress("banglore")
                .departmentCode("EC")
                .build();
        testEntityManager.persist(department);
    }

    @Test
    public void whenFindByIdReturnDepartment(){
        Department department=repository.findById(1l).get();
        assertEquals(department.getDepartmentName(),"Electronica communication");
    }
}