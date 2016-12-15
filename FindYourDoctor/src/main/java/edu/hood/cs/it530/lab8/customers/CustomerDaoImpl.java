package edu.hood.cs.it530.lab8.customers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;

@Component
public class CustomerDaoImpl extends AbstractDao implements CustomerDao {

    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Customer> getCustomersForABrach(String branchName) {

        String retrieveCustomersForABranch = "SELECT \n"
                + "    c.customer_name,      c.customer_street,     c.customer_city\n" + "FROM\n" + "    customer c\n"
                + "        JOIN\n" + "    borrower b ON c.customer_name = b.customer_name\n" + "        JOIN\n"
                + "    loan l ON l.loan_number = b.loan_number\n";
        if (branchName != null) {
            retrieveCustomersForABranch += "        AND l.branch_name = :branch_name";
        }

        Map<String, Object> namedParameters = new HashMap<>();
        if (branchName != null) {
            namedParameters.put("branch_name", branchName);

        }

        List<Customer> customers = getNamedParameterJdbcTemplate().query(retrieveCustomersForABranch, namedParameters,
                new CustomerMapper());

        return customers;

    }

    @Override
    @Transactional
    public List<Customer> insertCustomer(Customer customer) {

        String insertCustomerStatement = "insert into customer(customer_name, customer_street, customer_city)"
                + " values (:customer_name, :customer_street, :customer_city)";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("customer_name", customer.getName());
        namedParameters.put("customer_street", customer.getStreet());
        namedParameters.put("customer_city", customer.getCity());

        getNamedParameterJdbcTemplate().execute(insertCustomerStatement, namedParameters, ps -> ps.executeUpdate());
        
        String selectAllCustomers = "select from customer ";
        
        List<Customer> customers = getNamedParameterJdbcTemplate().query(selectAllCustomers, namedParameters,
                new CustomerMapper());
        
        return customers;

    }

}
