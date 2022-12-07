package com.upax.employeesservices.service;

import com.upax.employeesservices.domain.entity.*;

import java.util.List;

public interface EmployeeService {

    public List<FindEmployeeByJobResponse> findByJob(FindEmployeeByJobRequest empByJob);

    public EmployeesEntityResponse save(EmployeesEntityRequest employee);

    public EmployeeHrsWorkdResponse findHrsWorkd(EmployeeHrsWordRequest employeeHrsWordRequest);

    public EmployeeSalaryResponse findSalary(EmployeeSalaryRequest employeeSalaryRequest);

    public EmployeesResponse findEmployees(EmployeesRequest employeesRequest);
}
