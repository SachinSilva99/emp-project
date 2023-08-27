package com.sachin.employeeregister.api;

import com.sachin.employeeregister.dto.request.DepartmentRequestDTO;
import com.sachin.employeeregister.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO){
        departmentService.createDepartment(departmentRequestDTO);
    }
}
