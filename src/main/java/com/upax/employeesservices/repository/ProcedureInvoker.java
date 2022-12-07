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

    public List<FindEmployeeByJobResponse> findEmpByJob(FindEmployeeByJobRequest empByJob){
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
            return response;
    }

    public EmployeeHrsWorkdResponse findHrsWorkd(EmployeeHrsWordRequest employeeHrsWordRequest){
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SCHEMPLOYEES.PAEMPLOYEES.SPHRSXEMP");

        storedProcedureQuery.registerStoredProcedureParameter("EMPID", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("FECINICIAL", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("FECFINAL", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("HRSWORKD", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("RPS", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("MSG", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("EMPID", employeeHrsWordRequest.getEmployee_id())
                .setParameter("FECINICIAL",employeeHrsWordRequest.getStart_date())
                .setParameter("FECFINAL", employeeHrsWordRequest.getEnd_date())
                .execute();
        Integer hrsWork = (Integer) storedProcedureQuery.getOutputParameterValue("HRSWORKD");
        final String rps = (String) storedProcedureQuery.getOutputParameterValue("RPS");
        final String opMSG = (String) storedProcedureQuery.getOutputParameterValue("MSG");

        EmployeeHrsWorkdResponse ehwr = new EmployeeHrsWorkdResponse();
        if(hrsWork == 0){
            hrsWork = null;
        }
        ehwr.setTotal_worked_hours(hrsWork);
        ehwr.setSuccess(Boolean.valueOf(rps));
        return ehwr;
    }

    public EmployeeSalaryResponse findSalary(EmployeeSalaryRequest employeeSalaryRequest){
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SCHEMPLOYEES.PAEMPLOYEES.SPSALARYXEMP");

        storedProcedureQuery.registerStoredProcedureParameter("EMPID", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("FECINICIAL", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("FECFINAL", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("SALEMP", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("RPS", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("MSG", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("EMPID", employeeSalaryRequest.getEmployee_id())
                .setParameter("FECINICIAL",employeeSalaryRequest.getStart_date())
                .setParameter("FECFINAL", employeeSalaryRequest.getEnd_date())
                .execute();
        Integer salary = (Integer) storedProcedureQuery.getOutputParameterValue("SALEMP");
        final String rps = (String) storedProcedureQuery.getOutputParameterValue("RPS");
        final String opMSG = (String) storedProcedureQuery.getOutputParameterValue("MSG");

        EmployeeSalaryResponse esr = new EmployeeSalaryResponse();
        if(salary == 0){
            salary = null;
        }
        esr.setPayment(salary);
        esr.setSuccess(Boolean.valueOf(rps));
        return esr;
    }

    public EmployeeEntity findEmployees(EmployeeByDates employeesRequest){

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SCHEMPLOYEES.PAEMPLOYEES.SPFINDEMPBYDATE");

        storedProcedureQuery.registerStoredProcedureParameter("EMPID", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("FECINICIAL", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("FECFINAL", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("GENDERID", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("JOBID", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("NAME", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("LAST_NAME", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("BIRTHDATE", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("RSP", String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("MSG", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("EMPID", employeesRequest.getEmployee_id())
                .setParameter("FECINICIAL",employeesRequest.getStart_date())
                .setParameter("FECFINAL", employeesRequest.getEnd_date())
                .execute();
        Integer gender = (Integer) storedProcedureQuery.getOutputParameterValue("GENDERID");
        Integer job = (Integer) storedProcedureQuery.getOutputParameterValue("JOBID");
        final String name = (String) storedProcedureQuery.getOutputParameterValue("NAME");
        final String lastname = (String) storedProcedureQuery.getOutputParameterValue("LAST_NAME");
        final String birthdate = (String) storedProcedureQuery.getOutputParameterValue("BIRTHDATE");
        final String rps = (String) storedProcedureQuery.getOutputParameterValue("RSP");
        final String opMSG = (String) storedProcedureQuery.getOutputParameterValue("MSG");

        EmployeeEntity ee = new EmployeeEntity();
        ee.setGender_id(gender);
        ee.setJob_id(job);
        ee.setBirthdate(birthdate);
        ee.setName(name);
        ee.setLast_name(lastname);
        return ee;
    }
}
