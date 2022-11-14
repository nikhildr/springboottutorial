package com.skyfall.tutorial.springbootapplication.service;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import com.skyfall.tutorial.springbootapplication.exception.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {

    public Department saveDepartment(Department department);

    public List<Department> getAllDepartments();

    public boolean deleteDepartment(long id);

    public Department findById(long id) throws DepartmentNotFoundException;

    public Department updateDepartment(long id,Department department);

    public Department findDepartmentByCode(String departmentName);
}
