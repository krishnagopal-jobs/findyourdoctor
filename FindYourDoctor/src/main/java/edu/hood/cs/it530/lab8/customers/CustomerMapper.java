package edu.hood.cs.it530.lab8.customers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

        Customer customerBean = new Customer();

        customerBean.setName(resultSet.getString("customer_name"));
        customerBean.setStreet(resultSet.getString("customer_street"));
        customerBean.setCity(resultSet.getString("customer_city"));

        return customerBean;
    }

}
