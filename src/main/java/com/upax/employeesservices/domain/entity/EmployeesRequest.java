package com.upax.employeesservices.domain.entity;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeesRequest {
    private List<Integer> employee_id;
    private String start_date;
    private String end_date;
}
