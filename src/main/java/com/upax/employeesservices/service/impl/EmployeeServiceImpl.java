package com.upax.employeesservices.service.impl;

import com.upax.employeesservices.domain.entity.EmployeesEntityRequest;
import com.upax.employeesservices.domain.entity.EmployeesEntityResponse;
import com.upax.employeesservices.domain.entity.FindEmployeeByJobRequest;
import com.upax.employeesservices.repository.ProcedureInvoker;
import com.upax.employeesservices.service.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @PersistenceContext(name = "JPA_DEMO", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    public List<EmployeesEntityResponse> findByJob(FindEmployeeByJobRequest empByJob) {
        EmployeesEntityResponse eer = new EmployeesEntityResponse();
        ProcedureInvoker PI = new ProcedureInvoker(entityManager);
        eer = PI.findEmpByJob(empByJob);
        return null;
    }

    @Override
    public EmployeesEntityResponse save(EmployeesEntityRequest employee) {
        //employeesDAO.save(employee);
        EmployeesEntityResponse eer = new EmployeesEntityResponse();
        ProcedureInvoker PI = new ProcedureInvoker(entityManager);
        eer = PI.addEmployee(employee);
        return eer;
    }
}
