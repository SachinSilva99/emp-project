package com.sachin.employeeregister.service;



import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.request.DepartmentRequestDTO;
import com.sachin.employeeregister.dto.response.DepartmentResponseDTO;
import com.sachin.employeeregister.service.exception.UpdateFailedException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    void createDepartment(DepartmentRequestDTO departmentDTO);

    DepartmentResponseDTO updateDepartment(DepartmentDTO departmentDTO, Long id) throws UpdateFailedException;

    Optional<EmployeeDTO> getDepartment(Long id);

    void delete(Long id);

    List<EmployeeDTO> getAllEmployees();
}
