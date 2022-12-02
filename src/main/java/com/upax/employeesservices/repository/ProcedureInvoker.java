package com.upax.employeesservices.repository;

import com.upax.employeesservices.domain.entity.*;
import jakarta.persistence.Parameter;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProcedureInvoker {
    private EntityManager entityManager;

    public ProcedureInvoker(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public EmployeesEntityResponse addEmployee(EmployeesEntityRequest employeesEntity){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(employeesEntity.getBirthdate(), formatter);
        Period edad = Period.between(fechaNacimiento, LocalDate.now());
        if (edad.getYears() > 17){
            StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SCHEMPLOYEES.PAEMPLOYEES.SPADDEMPLOYEE");

            storedProcedureQuery.registerStoredProcedureParameter("GENDERID", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("JOBID", Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("NAME", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("LASTNAME", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("BIRTHDATE", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("EMPLOYEEID", Integer.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("RPS", String.class, ParameterMode.OUT);
            storedProcedureQuery.registerStoredProcedureParameter("MSG", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("GENDERID", employeesEntity.getGender_id()).setParameter("JOBID",employeesEntity.getJob_id()).setParameter("JOBID", employeesEntity.getJob_id()).setParameter("NAME", employeesEntity.getName()).setParameter("LASTNAME", employeesEntity.getLast_name()).setParameter("BIRTHDATE", employeesEntity.getBirthdate()).execute();
            final Integer empID = (Integer) storedProcedureQuery.getOutputParameterValue("EMPLOYEEID");
            final String rsp = (String) storedProcedureQuery.getOutputParameterValue("RPS");
            final String opMSG = (String) storedProcedureQuery.getOutputParameterValue("MSG");

            EmployeesEntityResponse eer = new EmployeesEntityResponse();
            eer.setId(Integer.valueOf(empID));
            eer.setSuccess(Boolean.valueOf(rsp));
            return eer;
        } else {
            EmployeesEntityResponse eer = new EmployeesEntityResponse();
            eer.setId(0);
            eer.setSuccess(false);
            return eer;
        }
    }

    public EmployeesEntityResponse findEmpByJob(FindEmployeeByJobRequest empByJob){
            /*StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SCHEMPLOYEES.PAEMPLOYEES.FNFINDEMPBYJOB");

            storedProcedureQuery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR);

            storedProcedureQuery.setParameter(0, empByJob.getJob_id()).execute();*/
            StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("SCHEMPLOYEES.PAEMPLOYEES.SPFINDEMPBYJOB")
                .registerStoredProcedureParameter(
                        1,
                        Integer.class,
                        ParameterMode.IN
                )
                .registerStoredProcedureParameter(
                        2,
                        Class.class,
                        ParameterMode.REF_CURSOR
                )
                .setParameter(empByJob.getJob_id(), 1);
            query.execute();
            List<FindEmployeeByJobBD> results = query.getResultList();
            List<FindEmployeeByJobResponse> response = results.stream().map(findEmployeeByJob ->
                    FindEmployeeByJobResponse.builder()
                    .id(findEmployeeByJob.getEmployeeid())
                    .name(findEmployeeByJob.getName())
                    .last_name(findEmployeeByJob.getLast_name())
                    .birthdate(findEmployeeByJob.getBirthdate())
                    .job(JobEntity.builder()
                            .id(findEmployeeByJob.getJobId())
                            .name(findEmployeeByJob.getNameJob())
                            .salary(findEmployeeByJob.getSalary())
                            .build())
                    .gender(GenderEntity.builder()
                            .id(findEmployeeByJob.getGenderId())
                            .name(findEmployeeByJob.getNameGender())
                            .build())
                    .build()).collect(Collectors.toList());

            EmployeesEntityResponse eer = new EmployeesEntityResponse();
            eer.setId(Integer.valueOf(0));
            eer.setSuccess(Boolean.valueOf(false));
            return eer;
    }
}
