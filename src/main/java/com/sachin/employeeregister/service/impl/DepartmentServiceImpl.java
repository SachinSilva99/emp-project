package com.sachin.employeeregister.service.impl;

import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.request.DepartmentRequestDTO;
import com.sachin.employeeregister.dto.response.DepartmentResponseDTO;
import com.sachin.employeeregister.entity.Department;
import com.sachin.employeeregister.repo.custom.DepartmentRepo;
import com.sachin.employeeregister.service.DepartmentService;
import com.sachin.employeeregister.service.exception.DuplicateException;
import com.sachin.employeeregister.service.exception.NotFoundException;
import com.sachin.employeeregister.service.exception.UpdateFailedException;
import com.sachin.employeeregister.util.FactoryConfiguration;
import com.sachin.employeeregister.util.mapper.DepartmentMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private FactoryConfiguration factoryConfiguration;

    @Override
    public void createDepartment(DepartmentRequestDTO dto) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(dto.getId());
        departmentDTO.setName(dto.getName());

        Session session = factoryConfiguration.getSession();
        boolean existByPk = departmentRepo.existByPk(departmentDTO.getId(), session);
        if (existByPk) {
            throw new DuplicateException(departmentDTO.getId() + " already exists");
        }
        Transaction transaction = session.beginTransaction();

        try {
            departmentRepo.save(departmentMapper.toDepartment(departmentDTO), session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentDTO departmentDTO, Long id) throws UpdateFailedException {
        Session session = factoryConfiguration.getSession();
        Optional<Department> existByPk = departmentRepo.findByPk(id, session);
        if (existByPk.isEmpty()) {
            throw new NotFoundException(departmentDTO.getId() + " not found");
        }
        Transaction transaction = session.beginTransaction();

        try {
            Department updatedDep = departmentMapper.toDepartment(departmentDTO);

            Department department = existByPk.get();
            department.setEmployeeList(updatedDep.getEmployeeList());
            department.setName(updatedDep.getName());

            departmentRepo.update(department, session);
            transaction.commit();
            return departmentMapper.toDepartmentResponseDto(department);

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw new UpdateFailedException("");
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<EmployeeDTO> getDepartment(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return null;
    }
}
