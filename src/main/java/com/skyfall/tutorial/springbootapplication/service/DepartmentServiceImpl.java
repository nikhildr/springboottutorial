package com.skyfall.tutorial.springbootapplication.service;

import com.skyfall.tutorial.springbootapplication.entity.Department;
import com.skyfall.tutorial.springbootapplication.exception.DepartmentNotFoundException;
import com.skyfall.tutorial.springbootapplication.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    @Override
    public boolean deleteDepartment(long id) {
        Optional<Department> department = repository.findById(id);
        if (department.isPresent()) {
            repository.delete(department.get());
            return true;
        }
        return false;
    }

    @Override
    public Department findById(long id) throws DepartmentNotFoundException {
        Optional<Department> department = repository.findById(id);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("department not available");
        }
        return department.get();
    }

    @Override
    public Department updateDepartment(long id, Department department) {
        Department departmentDb = repository.findById(id).get();
        departmentDb.setDepartmentCode(department.getDepartmentCode());
        departmentDb.setDepartmentAddress(department.getDepartmentAddress());
        repository.saveAndFlush(departmentDb);
        return departmentDb;
    }

    @Override
    public Department findDepartmentByCode(String departmentName) {
        return repository.findByDepartmentCode(departmentName);
    }

    @Override
    public List<Department> getAllDepartmentsByPage(Pageable pageable) {
        return (List<Department>) repository.findAll(pageable);
    }
}
