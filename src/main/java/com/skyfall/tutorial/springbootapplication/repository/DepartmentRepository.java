package com.skyfall.tutorial.springbootapplication.repository;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department findByDepartmentCode(String departmentCode);
}
