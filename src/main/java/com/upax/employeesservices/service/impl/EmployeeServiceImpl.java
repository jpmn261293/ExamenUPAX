package com.upax.employeesservices.service.impl;

import com.upax.employeesservices.domain.entity.*;
import com.upax.employeesservices.repository.ProcedureInvoker;
import com.upax.employeesservices.service.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @PersistenceContext(name = "JPA_DEMO", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    public List<FindEmployeeByJobResponse> findByJob(FindEmployeeByJobRequest empByJob) {
        FindEmployeeByJobResponse eer = new FindEmployeeByJobResponse();
        ProcedureInvoker PI = new ProcedureInvoker(entityManager);
        List<FindEmployeeByJobResponse> response = PI.findEmpByJob(empByJob);
        return response;
    }

    @Override
    public EmployeesEntityResponse save(EmployeesEntityRequest employee) {
        //employeesDAO.save(employee);
        EmployeesEntityResponse eer = new EmployeesEntityResponse();
        ProcedureInvoker PI = new ProcedureInvoker(entityManager);
        eer = PI.addEmployee(employee);
        return eer;
    }

    @Override
    public EmployeeHrsWorkdResponse findHrsWorkd(EmployeeHrsWordRequest employeeHrsWordRequest) {
        EmployeeHrsWorkdResponse ehwr = new EmployeeHrsWorkdResponse();
        ProcedureInvoker PI = new ProcedureInvoker(entityManager);
        ehwr = PI.findHrsWorkd(employeeHrsWordRequest);
        return ehwr;
    }

    @Override
    public EmployeeSalaryResponse findSalary(EmployeeSalaryRequest employeeSalaryRequest) {
        EmployeeSalaryResponse esr = new EmployeeSalaryResponse();
        ProcedureInvoker PI = new ProcedureInvoker(entityManager);

        esr = PI.findSalary(employeeSalaryRequest);
        return esr;
    }

    @Override
    public EmployeesResponse findEmployees(EmployeesRequest employeesRequest) {
        EmployeesResponse er = new EmployeesResponse();
        ProcedureInvoker PI = new ProcedureInvoker(entityManager);
        List<EmployeeEntity> leeResponse = employeesRequest.getEmployee_id().stream()
                .map(x -> PI.findEmployees(EmployeeByDates
                        .builder()
                        .employee_id(x)
                        .start_date(employeesRequest.getStart_date())
                        .end_date(employeesRequest.getEnd_date()).build())).collect(Collectors.toList());
        er.setEmployees(leeResponse);
        if(leeResponse.isEmpty()){
            er.setSuccess(false);
        }
        else {
            er.setSuccess(true);
        }

        return er;
    }
}
