package com.skyfall.tutorial.springbootapplication.service;

import com.skyfall.tutorial.springbootapplication.entity.Employee;
import com.skyfall.tutorial.springbootapplication.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Async
    public CompletableFuture<List<Employee>> saveCars(final InputStream inputStream) throws Exception {
        final long start = System.currentTimeMillis();

        log.info(" before Saving employee ");
        List<Employee> cars = parseCSVFile(inputStream);
        log.info("Saving employee , size {}",cars.size());

        log.info("Saving a list of cars of size {} records", cars.size());

        cars = employeeRepository.saveAll(cars);

        log.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(cars);
    }

    private List<Employee> parseCSVFile(final InputStream inputStream) throws Exception {
        final List<Employee> cars = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Employee employee = new Employee();
                    employee.setName(data[0]);
                    employee.setSalary(Integer.parseInt(data[1]));
                    cars.add(employee);
                }
                return cars;
            }
        } catch (final IOException e) {
            log.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
}
