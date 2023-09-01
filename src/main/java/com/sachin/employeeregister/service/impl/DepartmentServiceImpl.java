package com.sachin.employeeregister.service.impl;

import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.request.DepartmentRequestDTO;
import com.sachin.employeeregister.dto.response.DepartmentResponseDTO;
import com.sachin.employeeregister.entity.Department;
import com.sachin.employeeregister.repo.custom.DepartmentRepo;
import com.sachin.employeeregister.service.DepartmentService;
import com.sachin.employeeregister.service.exception.DuplicateException;
import com.sachin.employeeregister.service.exception.InUseException;
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
import java.util.stream.Collectors;

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
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO) throws UpdateFailedException {
        Session session = factoryConfiguration.getSession();
        Optional<Department> existByPk = departmentRepo.findByPk(departmentRequestDTO.getId(), session);
        if (existByPk.isEmpty()) {
            throw new NotFoundException(departmentRequestDTO.getId() + " not found");
        }
        Transaction transaction = session.beginTransaction();

        try {

            Department department = existByPk.get();
            department.setName(departmentRequestDTO.getName());
            departmentRepo.update(department, session);
            transaction.commit();
            return departmentMapper.toDepartmentResponseDto(department);

        } catch (Exception e) {
            transaction.rollback();
            throw new UpdateFailedException(e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<DepartmentResponseDTO> getDepartment(Long id) {
        Session session = factoryConfiguration.getSession();
        Optional<Department> byPk = departmentRepo.findByPk(id, session);
        session.close();
        return byPk.map(department -> departmentMapper.toDepartmentResponseDto(department));
    }

    @Override
    public void delete(Long id) {
        Session session = factoryConfiguration.getSession();
        Optional<Department> byPk = departmentRepo.findByPk(id, session);
        if (byPk.isEmpty()) {
            throw new NotFoundException(id + " not found");
        }
        Department department = byPk.get();
        Transaction transaction = session.beginTransaction();

        try {
            departmentRepo.delete(department, session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new InUseException(id + " in use");
        } finally {
            session.close();
        }

    }

    @Override
    public List<DepartmentResponseDTO> getAllEmployees() {
        Session session = factoryConfiguration.getSession();
        List<DepartmentResponseDTO> departmentResponseDTOS = departmentRepo.findAll(session)
                .stream()
                .map(department -> departmentMapper.toDepartmentResponseDto(department))
                .collect(Collectors.toList());
        session.close();
        return departmentResponseDTOS;
    }
}
