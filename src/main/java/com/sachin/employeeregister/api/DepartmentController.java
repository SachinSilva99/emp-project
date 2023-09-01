package com.sachin.employeeregister.api;

import com.sachin.employeeregister.dto.request.DepartmentRequestDTO;
import com.sachin.employeeregister.dto.response.DepartmentResponseDTO;
import com.sachin.employeeregister.service.DepartmentService;
import com.sachin.employeeregister.service.exception.DuplicateException;
import com.sachin.employeeregister.service.exception.InUseException;
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
@RequestMapping("api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        try {
            departmentService.createDepartment(departmentRequestDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DuplicateException e) {
            return new ResponseEntity<>(departmentRequestDTO.getId() + " already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentResponseDTO> getDepartment(@PathVariable Long departmentId) {
        Optional<DepartmentResponseDTO> department = departmentService.getDepartment(departmentId);

        return department
                .map(departmentResponseDTO -> new ResponseEntity<>(departmentResponseDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> delete(@PathVariable Long departmentId) {
        try {
            departmentService.delete(departmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InUseException e) {
            return new ResponseEntity<>(departmentId + " in use", HttpStatus.CONFLICT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(departmentId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody DepartmentRequestDTO departmentRequestDTO) {
        try {
            departmentService.updateDepartment(departmentRequestDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UpdateFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartmentResponseDTO>> findAll() {
        return new ResponseEntity<>(departmentService.getAllEmployees(), HttpStatus.OK);
    }
}
