package com.sachin.employeeregister.service;


import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.EmployeeDTO;

public interface EmployeeDepartmentService {
    void saveEmployeeWithDepartment(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO);

    void updateEmployeeWithDepartment(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO);
}
