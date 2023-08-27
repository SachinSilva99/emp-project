package com.sachin.employeeregister.util.mapper.impl;

import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.response.DepartmentResponseDTO;
import com.sachin.employeeregister.entity.Department;
import com.sachin.employeeregister.util.mapper.DepartmentMapper;
import com.sachin.employeeregister.util.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapperImpl implements DepartmentMapper {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public DepartmentDTO toDepartmentDto(Department dp) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(departmentDTO.getId());
        departmentDTO.setName(dp.getName());
        if (dp.getEmployee() != null) {
            departmentDTO.setEmployeeDTO(employeeMapper.toEmployeeDto(dp.getEmployee()));
        }
        return departmentDTO;
    }

    @Override
    public DepartmentResponseDTO toDepartmentResponseDto(Department department) {
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        department.setId(department.getId());
        department.setName(department.getName());
        if (department.getEmployee()!=null) {
            department.setEmployee(department.getEmployee());
        }
        return departmentResponseDTO;
    }

    @Override
    public Department toDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setName(departmentDTO.getName());
        if (departmentDTO.getEmployeeDTO() != null) {
            department.setEmployee(employeeMapper.toEmployee(departmentDTO.getEmployeeDTO()));
        }
        return department;
    }
}
