package com.sachin.employeeregister.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {
    private Long id;
    private String name;
    private List<EmployeeDTO> employeeDTOS = new ArrayList<>();
}
