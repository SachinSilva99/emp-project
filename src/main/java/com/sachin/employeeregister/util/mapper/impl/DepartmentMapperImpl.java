package com.sachin.employeeregister.util.mapper.impl;

import com.sachin.employeeregister.dto.DepartmentDTO;
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
    public DepartmentDTO toDepartmentDto(Department department) {
        return new DepartmentDTO(department.getId(), department.getName(), employeeMapper.toEmployeeDto(department.getEmployee()));
    }

    @Override
    public Department toDepartment(DepartmentDTO departmentDTO) {
        return new Department(departmentDTO.getId(), departmentDTO.getName(), employeeMapper.toEmployee(departmentDTO.getEmployeeDTO()));
    }
}
