package com.sachin.employeeregister.api;


import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveEmp(@RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeService.createEmployee(employeeDTO);
        } catch (Exception e) {
            ResponseEntity.ok();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmp(@PathVariable String id) {

      return null;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmp() {
        return null;
    }

}
