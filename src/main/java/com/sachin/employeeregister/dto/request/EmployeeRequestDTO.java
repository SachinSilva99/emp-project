package com.sachin.employeeregister.dto.request;

import com.sachin.employeeregister.dto.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequestDTO {
    private String id;
    private String name;
    private String email;
    private byte[] profile;

    public EmployeeRequestDTO(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}