package com.upax.employeesservices.domain.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeesResponse {
    private List<EmployeeEntity> employees;
    private Boolean success;
}
