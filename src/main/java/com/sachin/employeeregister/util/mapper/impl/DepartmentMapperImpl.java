package com.sachin.employeeregister.util.mapper.impl;

import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.response.DepartmentResponseDTO;
import com.sachin.employeeregister.entity.Department;
import com.sachin.employeeregister.util.mapper.DepartmentMapper;
import com.sachin.employeeregister.util.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DepartmentMapperImpl implements DepartmentMapper {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public DepartmentDTO toDepartmentDto(Department dp) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(dp.getId());
        departmentDTO.setName(dp.getName());
       /* if (dp.getEmployeeList() != null) {
            departmentDTO.setEmployeeDTOS(
                    dp.getEmployeeList()
                            .stream()
                            .map(employee -> employeeMapper.toEmployeeDto(employee))
                            .collect(Collectors.toList())
            );
        }*/
        return departmentDTO;
    }

    @Override
    public DepartmentResponseDTO toDepartmentResponseDto(Department department) {
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        department.setId(department.getId());
        department.setName(department.getName());
      /*  if (department.getEmployeeList() != null) {
            department.setEmployeeList(department.getEmployeeList());
        }*/
        return departmentResponseDTO;
    }

    @Override
    public Department toDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setName(departmentDTO.getName());
    /*    if (departmentDTO.getEmployeeDTOS() != null) {
            department.setEmployeeList(
                    departmentDTO.getEmployeeDTOS()
                            .stream().map(employeeDTO -> employeeMapper.toEmployee(employeeDTO))
                            .collect(Collectors.toList()));
        }*/
        return department;
    }
}
