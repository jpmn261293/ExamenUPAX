package com.upax.employeesservices.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeHrsWorkdResponse {
    private Integer total_worked_hours;
    private boolean success;
}
