package com.upax.employeesservices.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "EMPLOYEES", schema = "SCHEMPLOYEES", catalog = "")
public class EmployeesEntity{
    @Id
    @Column(name = "FIGENDERID")
    private Integer gender_id;
    @Column(name = "FIJOBID")
    private Integer job_id;
    @Column(name = "FCNAME")
    private String name;
    @Column(name = "FCLASTNAME")
    private String last_name;
    @Column(name = "FDBIRTHDATE")
    private String birthdate;

    public EmployeesEntity() {}

    public EmployeesEntity(Integer gender_id, Integer job_id, String name, String last_name, String birthdate) {
        this.gender_id = gender_id;
        this.job_id = job_id;
        this.name = name;
        this.last_name = last_name;
        this.birthdate = birthdate;
    }

    public Integer getFigenderid() {
        return gender_id;
    }

    public void setFigenderid(Integer gender_id) {
        this.gender_id = gender_id;
    }

    public Integer getFijobid() {
        return job_id;
    }

    public void setFijobid(Integer job_id) {
        this.job_id = job_id;
    }

    public String getFcname() {
        return name;
    }

    public void setFcname(String name) {
        this.name = name;
    }

    public String getFclastname() {
        return last_name;
    }

    public void setFclastname(String last_name) {
        this.last_name = last_name;
    }

    public String getFdbirthdate() {
        return birthdate;
    }

    public void setFdbirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "EmployeesEntity [gender_id=" + gender_id + ", job_id=" + job_id + ", name=" + name
                + ", last_name=" + last_name + ", birthdate=" + birthdate +"]";
    }
}
