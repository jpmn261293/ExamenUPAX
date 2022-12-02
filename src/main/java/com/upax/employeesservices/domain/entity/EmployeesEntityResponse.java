package com.upax.employeesservices.domain.entity;

public class EmployeesEntityResponse {
    private Integer id;
    private boolean success;

    public EmployeesEntityResponse() {
        this.id = id;
        this.success = success;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
