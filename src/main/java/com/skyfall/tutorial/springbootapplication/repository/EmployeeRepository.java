package com.skyfall.tutorial.springbootapplication.repository;

import com.skyfall.tutorial.springbootapplication.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
