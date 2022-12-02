package com.upax.employeesservices.repository;

import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;

public class Connection extends StoredProcedure {

    public Connection (DataSource dataSource, String procedureName) {
        super(dataSource, procedureName);
    }

    public Connection (DataSource dataSource, String procedureName, boolean isFunction){
        super(dataSource, procedureName);
        super.setFunction(isFunction);
    }
}
