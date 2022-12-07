package com.upax.employeesservices.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeByDates {
    private Integer employee_id;
    private String start_date;
    private String end_date;
}
