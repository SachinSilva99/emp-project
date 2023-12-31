package com.sachin.employeeregister.util.mapper;

import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.response.EmployeeResponseDTO;
import com.sachin.employeeregister.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface EmployeeMapper {

    EmployeeDTO toEmployeeDto(Employee employee);
    Employee toEmployee(EmployeeDTO employeeDTO);
    EmployeeResponseDTO toEmployeeResponseDto(Employee employee);
}
