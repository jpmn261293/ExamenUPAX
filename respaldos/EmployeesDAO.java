package com.upax.employeesservices.domain.dao;

import java.math.BigInteger;
import java.util.List;

public interface EmployeesDAO {

    //public List<EmployeesEntity> findByJob(BigInteger job);

    public void save(EmployeesEntity employee);

}
