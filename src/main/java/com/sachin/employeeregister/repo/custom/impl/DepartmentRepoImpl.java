package com.sachin.employeeregister.repo.custom.impl;


import com.sachin.employeeregister.entity.Department;
import com.sachin.employeeregister.entity.Employee;
import com.sachin.employeeregister.repo.ConstraintViolationException;
import com.sachin.employeeregister.repo.custom.DepartmentRepo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;


@Component
public class DepartmentRepoImpl implements DepartmentRepo {
    @Override
    public Department save(Department department, Session session) throws ConstraintViolationException {
        try {
            session.save(department);
            return department;
        } catch (Exception e) {
            throw new ConstraintViolationException("Did not saved department");
        }    }

    @Override
    public Department update(Department department, Session session) throws ConstraintViolationException {
        try {
            session.save(department);
            return department;
        } catch (Exception e) {
            throw new ConstraintViolationException("Did not saved department");
        }
    }

    @Override
    public void delete(Department department, Session session) throws ConstraintViolationException {
        try {
            session.delete(department);
        } catch (Exception e) {
            throw new ConstraintViolationException("Did not delete department");
        }
    }

    @Override
    public List<Department> findAll(Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
        query.from(Employee.class);
        return session.createQuery(query).getResultList();
    }

    @Override
    public Optional<Department> findByPk(Long pk, Session session) {
        Department department = session.get(Department.class, pk);
        return department == null ? Optional.empty() : Optional.of(department);
    }

    @Override
    public boolean existByPk(Long pk, Session session) {
        return session.get(Department.class, pk) != null;
    }

    @Override
    public long count(Session session) {
        String hql = "SELECT COUNT(*) FROM Department";
        Query<Long> query = session.createQuery(hql, Long.class);
        return query.uniqueResult();
    }
}
