package com.upax.employeesservices.repository;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

public class CallSP {
    DataSource ds;

    @Test
    public void getDataSource() throws SQLException{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ds = (DataSource)ctx.getBean("dataSource");

    }
}
