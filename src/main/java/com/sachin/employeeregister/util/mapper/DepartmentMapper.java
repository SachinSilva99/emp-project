package com.sachin.employeeregister.util.mapper;

import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.entity.Department;

public interface DepartmentMapper {
    DepartmentDTO toDepartmentDto(Department department);
    Department toDepartment(DepartmentDTO departmentDTO);
}
