package com.sachin.employeeregister.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private String id;
    private String name;
    private String email;
    private byte[] profile;
    private DepartmentDTO departmentDTO;


    public EmployeeDTO(String id, String name, String email, byte[] profile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

}
