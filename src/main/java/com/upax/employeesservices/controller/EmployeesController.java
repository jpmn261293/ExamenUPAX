package com.upax.employeesservices.controller;

import com.upax.employeesservices.domain.entity.*;
import com.upax.employeesservices.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/byJob")
    public List<FindEmployeeByJobResponse> findEmpByJob(@RequestBody FindEmployeeByJobRequest empByJob){
        List<FindEmployeeByJobResponse> response = employeeService.findByJob(empByJob);
        return response;
    }

    @PostMapping("/addEmployee")
    public EmployeesEntityResponse addEmployees(@RequestBody EmployeesEntityRequest employeesEntityRequest){
        EmployeesEntityResponse eer = new EmployeesEntityResponse();

        eer = employeeService.save(employeesEntityRequest);

        return eer;
    }

    @PostMapping("/findHW")
    public EmployeeHrsWorkdResponse findHrsWorkd(@RequestBody EmployeeHrsWordRequest employeeHrsWordRequest){
        EmployeeHrsWorkdResponse ehwr = new EmployeeHrsWorkdResponse();
        ehwr = employeeService.findHrsWorkd(employeeHrsWordRequest);
        return ehwr;
    }

    @PostMapping("/findSalary")
    public EmployeeSalaryResponse findSalary(@RequestBody EmployeeSalaryRequest employeeSalaryRequest){
        EmployeeSalaryResponse esr = new EmployeeSalaryResponse();
        esr = employeeService.findSalary(employeeSalaryRequest);
        return esr;
    }

    @PostMapping("/findEmployees")
    public EmployeesResponse findEmployees(@RequestBody EmployeesRequest employeesRequest){
        EmployeesResponse er = new EmployeesResponse();
        er = employeeService.findEmployees(employeesRequest);
        return er;
    }
}
