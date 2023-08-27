package com.sachin.employeeregister.service;



import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    void createDepartment(DepartmentDTO departmentDTO);

    EmployeeDTO updateDepartment(DepartmentDTO departmentDTO, String id);

    Optional<EmployeeDTO> getDepartment(Long id);

    void delete(String id);

    List<EmployeeDTO> getAllEmployees();
}
