package com.sachin.employeeregister.dto.response;

import com.sachin.employeeregister.dto.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeResponseDTO {
    private String id;
    private String name;
    private String email;
    private byte[] profile;
    private DepartmentDTO departmentDTO;

    public EmployeeResponseDTO(String id, String name, String email, byte[] profile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }
}
