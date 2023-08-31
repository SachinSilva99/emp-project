package com.sachin.employeeregister.dto.response;

import com.sachin.employeeregister.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private List<EmployeeDTO> employeeDTOS;
}
