package com.sachin.employeeregister.util.mapper.impl;

import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.entity.Employee;
import com.sachin.employeeregister.util.mapper.DepartmentMapper;
import com.sachin.employeeregister.util.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public EmployeeDTO toEmployeeDto(Employee emp) {
        return new EmployeeDTO(
                emp.getId(),
                emp.getName(),
                emp.getEmail(),
                emp.getProfile(),
                departmentMapper.toDepartmentDto(emp.getDepartment())
        );
    }

    @Override
    public Employee toEmployee(EmployeeDTO empDto) {
        return new Employee(
                empDto.getId(),
                empDto.getName(),
                empDto.getEmail(),
                empDto.getProfile(),
                departmentMapper.toDepartment(empDto.getDepartmentDTO())
        );
    }
}
