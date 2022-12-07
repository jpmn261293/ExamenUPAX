package com.upax.employeesservices.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeEntity {
    private Integer gender_id;
    private Integer job_id;
    private String name;
    private String last_name;
    private String birthdate;
}
