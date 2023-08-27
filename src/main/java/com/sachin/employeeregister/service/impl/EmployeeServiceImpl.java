package com.sachin.employeeregister.service.impl;


import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.repo.custom.EmployeeRepo;
import com.sachin.employeeregister.service.EmployeeService;
import com.sachin.employeeregister.service.exception.DuplicateException;
import com.sachin.employeeregister.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;


    @Autowired
    private FactoryConfiguration factoryConfiguration;



    @Override
    public void createEmployee(EmployeeDTO employeeDTO) throws DuplicateException {
        System.out.println(employeeDTO);
/*        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        String name = employeeDTO1.getName();*//*
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        boolean existByPk = employeeRepo.existByPk(employeeDTO.getId(), session);
        if (existByPk) {
            throw new DuplicateException(employeeDTO.getId() + employeeDTO.getName() + " already exists");
        }
        employeeRepo.save(employeeMapper.toEmployee(employeeDTO), session);
        transaction.commit();*/
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id) {
        return null;
    }

    @Override
    public Optional<EmployeeDTO> getEmployee(Long id) {
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
