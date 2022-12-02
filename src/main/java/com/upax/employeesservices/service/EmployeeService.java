package com.upax.employeesservices.service;

import com.upax.employeesservices.domain.entity.EmployeesEntityRequest;
import com.upax.employeesservices.domain.entity.EmployeesEntityResponse;
import com.upax.employeesservices.domain.entity.FindEmployeeByJobRequest;

import java.util.List;

public interface EmployeeService {

    public List<EmployeesEntityResponse> findByJob(FindEmployeeByJobRequest empByJob);

    public EmployeesEntityResponse save(EmployeesEntityRequest employee);
}
