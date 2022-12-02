package com.upax.employeesservices.domain.dao.impl;

import com.upax.employeesservices.domain.dao.EmployeesDAO;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeesDAOImpl implements EmployeesDAO {

    @Autowired
    private EntityManager entityManager;
/*
    @Override
    public List<EmployeesEntity> findByJob(int job) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<EmployeesEntity> theQuery = (Query<EmployeesEntity>) currentSession.get(EmployeesEntity.class, job);

        List<EmployeesEntity> employee = theQuery.getResultList();

        return employee;
    }*/

    @Override
    public void save(EmployeesEntity employee) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(employee);
    }
}
