package com.upax.employeesservices.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JobEntity {
    private Integer id;
    private String name;
    private Double salary;

}
