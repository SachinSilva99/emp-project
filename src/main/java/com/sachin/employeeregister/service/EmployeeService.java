package com.sachin.employeeregister.service;


import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.request.EmployeeRequestDTO;
import com.sachin.employeeregister.dto.response.EmployeeResponseDTO;
import com.sachin.employeeregister.service.exception.UpdateFailedException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void createEmployee(EmployeeRequestDTO employeeRequestDTO);

    void updateEmployee(EmployeeRequestDTO employeeDTO, String id, Long departmentId) throws UpdateFailedException;

    Optional<EmployeeResponseDTO> getEmployee(String id);



    void delete(String id);

    List<EmployeeResponseDTO> getAllEmployees();
}
