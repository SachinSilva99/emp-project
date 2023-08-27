package com.sachin.employeeregister.service;


import com.sachin.employeeregister.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id);

    Optional<EmployeeDTO> getEmployee(Long id);

    void delete(Long id);

    List<EmployeeDTO> getAllEmployees();
}
