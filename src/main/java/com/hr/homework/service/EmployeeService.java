package com.hr.homework.service;

import com.hr.homework.entity.Employee;
import com.hr.homework.utils.EmployeeSearchParams;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();

    void save(Employee employee);

    void delete(Employee employee);

    Employee findById(Long id);

    /**
     * Performs search by filtering params.
     * @param params optional params from request
     * @return List<Employee> that matches filter
     */
    List<Employee> searchForEmployee(EmployeeSearchParams params);
}
