package com.skyfall.tutorial.springbootapplication.service;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import com.skyfall.tutorial.springbootapplication.exception.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    boolean deleteDepartment(long id);

    Department findById(long id) throws DepartmentNotFoundException;

    Department updateDepartment(long id, Department department);

    Department findDepartmentByCode(String departmentName);
}
