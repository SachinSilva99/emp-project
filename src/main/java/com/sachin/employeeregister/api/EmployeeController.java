package com.sachin.employeeregister.api;


import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.request.EmployeeRequestDTO;
import com.sachin.employeeregister.service.EmployeeService;
import com.sachin.employeeregister.service.exception.DuplicateException;
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
    public void saveEmp(@RequestBody EmployeeRequestDTO dto) {
        try {
            EmployeeDTO employeeDTO = new EmployeeDTO(dto.getId(), dto.getName(), dto.getEmail(), dto.getProfile());
            employeeService.createEmployee(employeeDTO);
            ResponseEntity.ok();
        } catch (DuplicateException e) {
            e.printStackTrace();
            ResponseEntity.badRequest();
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
