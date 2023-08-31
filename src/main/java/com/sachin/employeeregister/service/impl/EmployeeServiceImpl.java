package com.sachin.employeeregister.service.impl;


import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.dto.request.EmployeeRequestDTO;
import com.sachin.employeeregister.dto.response.EmployeeResponseDTO;
import com.sachin.employeeregister.entity.Department;
import com.sachin.employeeregister.entity.Employee;
import com.sachin.employeeregister.repo.ConstraintViolationException;
import com.sachin.employeeregister.repo.custom.DepartmentRepo;
import com.sachin.employeeregister.repo.custom.EmployeeRepo;
import com.sachin.employeeregister.service.EmployeeService;
import com.sachin.employeeregister.service.exception.DuplicateException;
import com.sachin.employeeregister.service.exception.NotFoundException;
import com.sachin.employeeregister.service.exception.UpdateFailedException;
import com.sachin.employeeregister.util.FactoryConfiguration;
import com.sachin.employeeregister.util.mapper.DepartmentMapper;
import com.sachin.employeeregister.util.mapper.EmployeeMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private FactoryConfiguration factoryConfiguration;
    @Autowired
    private DepartmentRepo departmentRepo;


    @Override
    public void createEmployee(EmployeeRequestDTO dto) throws DuplicateException {
        EmployeeDTO employeeDTO = new EmployeeDTO(dto.getId(), dto.getName(), dto.getEmail(), dto.getProfile());

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        boolean existByPk = employeeRepo.existByPk(employeeDTO.getId(), session);
        if (existByPk) {
            throw new DuplicateException(employeeDTO.getId() + employeeDTO.getName() + " already exists");
        }

        try {
            System.out.println(employeeDTO);
            employeeRepo.save(employeeMapper.toEmployee(employeeDTO), session);
            transaction.commit();
            System.out.println("saved");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }


    @Override
    public void updateEmployee(EmployeeRequestDTO dto, String id, Long departmentId) throws UpdateFailedException, NotFoundException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        Optional<Employee> existByPk = employeeRepo.findByPk(id, session);
        if (existByPk.isEmpty()) {
            System.out.println("empty");
            throw new NotFoundException(id + " not found");
        }

        try {
            Employee employee = existByPk.get();
            employee.setName(dto.getName());
            employee.setEmail(dto.getEmail());
            employee.setProfile(dto.getProfile());

            if (departmentId != null) {
                Optional<Department> byPk = departmentRepo.findByPk(departmentId, session);
                byPk.ifPresent(employee::setDepartment);
            }

            employeeRepo.update(employee, session);
            transaction.commit();
            employeeMapper.toEmployeeResponseDto(employee);
        } catch (Exception e) {
            transaction.rollback();
            throw new UpdateFailedException(id + " failed to update");
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<EmployeeResponseDTO> getEmployee(String id) {
        Session session = factoryConfiguration.getSession();
        Optional<Employee> byPk = employeeRepo.findByPk(id, session);
        session.close();
        return byPk.map(employee -> employeeMapper.toEmployeeResponseDto(employee));
    }

    @Override
    public void delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        Optional<Employee> byPk = employeeRepo.findByPk(id, session);
        if (byPk.isEmpty()) {
            throw new NotFoundException(id + " employee not found");
        }
        try {
            transaction.begin();
            employeeRepo.delete(byPk.get(), session);
        } catch (Exception e) {
            transaction.rollback();
            throw new ConstraintViolationException(id + " employee in use");
        } finally {
            session.close();
        }

    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        Session session = factoryConfiguration.getSession();
        List<EmployeeResponseDTO> employeeDTOS = employeeRepo.findAll(session)
                .stream()
                .map(employee -> employeeMapper.toEmployeeResponseDto(employee))
                .collect(Collectors.toList());
        session.close();
        return employeeDTOS;
    }
}
