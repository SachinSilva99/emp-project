package com.sachin.employeeregister.api;


import com.mysql.cj.log.Log;
import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.request.EmployeeRequestDTO;
import com.sachin.employeeregister.service.EmployeeService;
import com.sachin.employeeregister.service.exception.DuplicateException;
import com.sachin.employeeregister.service.exception.UpdateFailedException;
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
            employeeService.createEmployee(dto);
            ResponseEntity.ok();
        } catch (DuplicateException e) {
            e.printStackTrace();
            ResponseEntity.badRequest();
        }
    }

    @PutMapping(value = {"/{id}", "/{id}/{departmentId}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEmployee(
            @RequestBody EmployeeRequestDTO dto,
            @PathVariable String id,
            @PathVariable(required = false) String departmentId
    ) {
        try {
            Long depId = null;
            if (departmentId != null) {
                depId = Long.valueOf(departmentId);
            }
            employeeService.updateEmployee(dto, id, depId);
        } catch (UpdateFailedException e) {
            throw new RuntimeException(e);
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
