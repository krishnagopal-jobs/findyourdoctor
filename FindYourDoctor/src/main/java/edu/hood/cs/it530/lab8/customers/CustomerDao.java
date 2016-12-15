/**
 * 
 */
package edu.hood.cs.it530.lab8.customers;

import java.util.List;

/**
 * @author kisna
 *
 */
public interface CustomerDao {

    List<Customer> getCustomersForABrach(String branchName);
    
    List<Customer> insertCustomer(Customer customer);
    
}
