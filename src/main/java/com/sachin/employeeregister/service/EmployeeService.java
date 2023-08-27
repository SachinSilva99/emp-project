package com.sachin.employeeregister.service;


import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.request.EmployeeRequestDTO;
import com.sachin.employeeregister.service.exception.UpdateFailedException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void createEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id) throws UpdateFailedException;

    Optional<EmployeeDTO> getEmployee(String id);



    void delete(String id);

    List<EmployeeDTO> getAllEmployees();
}
