package com.sachin.employeeregister.service;


import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.request.EmployeeRequestDTO;
import com.sachin.employeeregister.dto.response.EmployeeResponseDTO;
import com.sachin.employeeregister.service.exception.UpdateFailedException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void createEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO updateEmployee(EmployeeDTO employeeDTO, Long id) throws UpdateFailedException;

    Optional<EmployeeResponseDTO> getEmployee(String id);



    void delete(String id);

    List<EmployeeResponseDTO> getAllEmployees();
}
