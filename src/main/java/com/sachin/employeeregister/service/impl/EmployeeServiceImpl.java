package com.sachin.employeeregister.service.impl;


import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.entity.Employee;
import com.sachin.employeeregister.repo.ConstraintViolationException;
import com.sachin.employeeregister.repo.custom.EmployeeRepo;
import com.sachin.employeeregister.service.EmployeeService;
import com.sachin.employeeregister.service.exception.DuplicateException;
import com.sachin.employeeregister.service.exception.NotFoundException;
import com.sachin.employeeregister.service.exception.UpdateFailedException;
import com.sachin.employeeregister.util.FactoryConfiguration;
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
    private FactoryConfiguration factoryConfiguration;


    @Override
    public void createEmployee(EmployeeDTO employeeDTO) throws DuplicateException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        boolean existByPk = employeeRepo.existByPk(employeeDTO.getId(), session);
        if (existByPk) {
            throw new DuplicateException(employeeDTO.getId() + employeeDTO.getName() + " already exists");
        }
        try {
            employeeRepo.save(employeeMapper.toEmployee(employeeDTO), session);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id) throws UpdateFailedException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        Optional<Employee> existByPk = employeeRepo.findByPk(employeeDTO.getId(), session);
        if (existByPk.isEmpty()) {
            throw new NotFoundException(employeeDTO.getId() + employeeDTO.getName() + " not found");
        }

        try {
            Employee employee = existByPk.get();
            employee.setName(employee.getName());
            employee.setEmail(employee.getEmail());
            employee.setProfile(employee.getProfile());
            employee.setDepartment(employee.getDepartment());
            employeeRepo.update(employee, session);
            transaction.commit();
            return employeeMapper.toEmployeeDto(employee);
        } catch (Exception e) {
            transaction.rollback();
            throw new UpdateFailedException(employeeDTO + " failed to update");
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<EmployeeDTO> getEmployee(String id) {
        Session session = factoryConfiguration.getSession();
        Optional<Employee> byPk = employeeRepo.findByPk(id, session);
        session.close();
        return byPk.map(employee -> employeeMapper.toEmployeeDto(employee));
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
    public List<EmployeeDTO> getAllEmployees() {
        Session session = factoryConfiguration.getSession();
        List<EmployeeDTO> employeeDTOS = employeeRepo.findAll(session)
                .stream()
                .map(employee -> employeeMapper.toEmployeeDto(employee))
                .collect(Collectors.toList());
        session.close();
        return employeeDTOS;
    }
}
