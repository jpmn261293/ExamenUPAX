package com.upax.employeesservices.domain.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FindEmployeeByJobResponse {
    private Integer id;
    private String name;
    private String last_name;
    private String birthdate;
    private JobEntity job;
    private GenderEntity gender;
}
