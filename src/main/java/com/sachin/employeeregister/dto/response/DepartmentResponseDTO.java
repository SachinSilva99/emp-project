package com.sachin.employeeregister.dto.response;

import com.sachin.employeeregister.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private EmployeeDTO employeeDTO;
}
