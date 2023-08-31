package com.sachin.employeeregister.repo.custom.impl;


import com.sachin.employeeregister.entity.Employee;
import com.sachin.employeeregister.repo.ConstraintViolationException;
import com.sachin.employeeregister.repo.custom.EmployeeRepo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRepoImpl implements EmployeeRepo {

    @Override
    public Employee save(Employee employee, Session session) throws ConstraintViolationException {
        try {
            session.save(employee);
            return employee;
        } catch (Exception e) {
            throw new ConstraintViolationException("Did not saved employee");
        }
    }

    @Override
    public Employee update(Employee employee, Session session) throws ConstraintViolationException {
        try {
            session.save(employee);
            return employee;
        } catch (Exception e) {
            throw new ConstraintViolationException("Did not saved employee");
        }
    }

    @Override
    public void delete(Employee employee, Session session) throws ConstraintViolationException {
        try {
            session.delete(employee);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConstraintViolationException("Did not delete customer");
        }
    }

    @Override
    public List<Employee> findAll(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        query.from(Employee.class);
        return session.createQuery(query).getResultList();
    }

    @Override
    public Optional<Employee> findByPk(String pk, Session session) {
        Employee employee = session.get(Employee.class, pk);
        return employee == null ? Optional.empty() : Optional.of(employee);
    }

    @Override
    public boolean existByPk(String pk, Session session) {
        Employee employee = session.get(Employee.class, pk);
        return employee != null;
    }

    @Override
    public long count(Session session) {
        String hql = "SELECT COUNT(*) FROM Employee";
        Query<Long> query = session.createQuery(hql, Long.class);
        return query.uniqueResult();
    }
}
