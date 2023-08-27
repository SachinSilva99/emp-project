package com.sachin.employeeregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {
    private Long id;
    private String name;
    private EmployeeDTO employeeDTO;
}
