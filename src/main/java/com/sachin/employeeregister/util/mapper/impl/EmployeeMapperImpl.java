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
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(emp.getId());
        employeeDTO.setName(emp.getName());
        employeeDTO.setEmail(emp.getEmail());
        employeeDTO.setProfile(emp.getProfile());
        if (emp.getDepartment() != null) {
            employeeDTO.setDepartmentDTO(departmentMapper.toDepartmentDto(emp.getDepartment()));
        }
        return employeeDTO;
    }

    @Override
    public Employee toEmployee(EmployeeDTO empDto) {
        Employee employee = new Employee();
        employee.setId(empDto.getId());
        employee.setName(empDto.getName());
        employee.setEmail(empDto.getEmail());
        employee.setProfile(empDto.getProfile());
        if (empDto.getDepartmentDTO() != null) {
            employee.setDepartment(departmentMapper.toDepartment(empDto.getDepartmentDTO()));
        }
        return employee;
    }
}
