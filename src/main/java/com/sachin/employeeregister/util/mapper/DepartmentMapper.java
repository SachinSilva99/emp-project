package com.sachin.employeeregister.util.mapper;

import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.response.DepartmentResponseDTO;
import com.sachin.employeeregister.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


public interface DepartmentMapper {

    DepartmentDTO toDepartmentDto(Department department);
    DepartmentResponseDTO toDepartmentResponseDto(Department department);
    Department toDepartment(DepartmentDTO departmentDTO);
}
