package com.upax.employeesservices.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeesEntityRequest {

    private Integer gender_id;

    private Integer job_id;

    private String name;

    private String last_name;

    private String birthdate;


}
