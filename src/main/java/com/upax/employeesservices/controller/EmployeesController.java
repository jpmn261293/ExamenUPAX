package com.upax.employeesservices.controller;

import com.upax.employeesservices.domain.entity.EmployeesEntityRequest;
import com.upax.employeesservices.domain.entity.EmployeesEntityResponse;
import com.upax.employeesservices.domain.entity.FindEmployeeByJobRequest;
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
    public List<EmployeesEntityResponse> findEmpByJob(@RequestBody FindEmployeeByJobRequest empByJob){
        employeeService.findByJob(empByJob);
        return null;
    }

    @PostMapping("/addEmployee")
    public EmployeesEntityResponse addEmployees(@RequestBody EmployeesEntityRequest employeesEntityRequest){
        EmployeesEntityResponse eer = new EmployeesEntityResponse();

        eer = employeeService.save(employeesEntityRequest);

        return eer;
    }
}
