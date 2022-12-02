package com.upax.employeesservices.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FindEmployeeByJobBD {
        @Id
        @Column(name = "EMPLOYEES.FIEMPLOYEEID")
        private Integer Employeeid;

        @Column(name = "EMPLOYEES.FCNAME")
        private String name;

        @Column(name = "EMPLOYEES.FCLASTNAME")
        private String last_name;

        @Column(name = "EMPLOYEES.FDBIRTHDATE")
        private String birthdate;

        @Column(name = "JOBS.FIJOBID")
        private Integer jobId;

        @Column(name = "JOBS.FCNAME")
        private String nameJob;

        @Column(name = "JOBS.FNSALARY")
        private Double Salary;

        @Column(name = "GENDERS.FIGENDERID")
        private Integer genderId;

        @Column(name = "GENDERS.FCNAME")
        private String nameGender;
}
