package com.sachin.employeeregister.api;


import com.sachin.employeeregister.dto.request.EmployeeRequestDTO;
import com.sachin.employeeregister.dto.response.EmployeeResponseDTO;
import com.sachin.employeeregister.repo.ConstraintViolationException;
import com.sachin.employeeregister.service.EmployeeService;
import com.sachin.employeeregister.service.exception.DuplicateException;
import com.sachin.employeeregister.service.exception.NotFoundException;
import com.sachin.employeeregister.service.exception.UpdateFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = {"", "/{departmentId}"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createEmployee(
            @RequestPart String id,
            @RequestPart String name,
            @RequestPart String email,
            @RequestPart(required = false) byte[] profile,
            @PathVariable(required = false) Long departmentId) {

        try {
            EmployeeRequestDTO dto = new EmployeeRequestDTO(id, name, email);
            if (profile != null) {
                dto.setProfile(profile);
            }

            employeeService.createEmployee(dto, departmentId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DuplicateException e) {
            return new ResponseEntity<>(id + " employee Already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = {"", "/{departmentId}"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateEmployee(
            @RequestPart String id,
            @RequestPart String name,
            @RequestPart String email,
            @RequestPart(required = false) byte[] profile,
            @PathVariable(required = false) String departmentId
    ) {
        try {
            EmployeeRequestDTO dto = new EmployeeRequestDTO(id, name, email);
            if (profile != null) {
                dto.setProfile(profile);
            }

            Long depId = null;
            if (departmentId != null) {
                depId = Long.valueOf(departmentId);
            }
            employeeService.updateEmployee(dto, id, depId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UpdateFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(id + " not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmp(@PathVariable String id) {
        Optional<EmployeeResponseDTO> employee = employeeService.getEmployee(id);
        return employee.map(
                        employeeResponseDTO -> new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmp() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        try {
            System.out.println(id);
            employeeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>("employee in use ", HttpStatus.CONFLICT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
