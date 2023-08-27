package com.sachin.employeeregister.service.impl;


import com.sachin.employeeregister.dto.DepartmentDTO;
import com.sachin.employeeregister.dto.EmployeeDTO;
import com.sachin.employeeregister.entity.Department;
import com.sachin.employeeregister.entity.Employee;
import com.sachin.employeeregister.repo.custom.DepartmentRepo;
import com.sachin.employeeregister.repo.custom.EmployeeRepo;
import com.sachin.employeeregister.service.EmployeeDepartmentService;
import com.sachin.employeeregister.util.FactoryConfiguration;
import com.sachin.employeeregister.util.mapper.DepartmentMapper;
import com.sachin.employeeregister.util.mapper.EmployeeMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDepartmentServiceImpl implements EmployeeDepartmentService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;


    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;


    @Autowired
    private FactoryConfiguration factoryConfiguration;

    @Override
    public void saveEmployeeWithDepartment(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Employee employee = employeeMapper.toEmployee(employeeDTO);
            Department department = departmentMapper.toDepartment(departmentDTO);
            employee.setDepartment(department);
            department.setEmployee(employee);
            employeeRepo.save(employee, session);
            departmentRepo.save(department, session);
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void updateEmployeeWithDepartment(EmployeeDTO employeeDTO, DepartmentDTO departmentDTO) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Employee employee = employeeMapper.toEmployee(employeeDTO);
            Department department = departmentMapper.toDepartment(departmentDTO);
            employee.setDepartment(department);
            department.setEmployee(employee);
            employeeRepo.update(employee, session);
            departmentRepo.update(department, session);
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
