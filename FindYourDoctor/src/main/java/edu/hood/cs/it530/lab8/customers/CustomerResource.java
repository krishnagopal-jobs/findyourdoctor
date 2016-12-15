package edu.hood.cs.it530.lab8.customers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.Result;

@Path("customers")
@Component
public class CustomerResource {

    Logger logger = LoggerFactory.getLogger(CustomerResource.class);
    
    @Autowired
    private CustomerDao customerDao;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/branches/{branchName}")
    public Result<Customer> retrieveCustomerOfAbranch(@PathParam("branchName") String branchName) {

        List<Customer> customers = customerDao.getCustomersForABrach(branchName);
        
        Result<Customer> customersResult = new Result<>();
        customersResult.setData(customers);
        
        return customersResult;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result<Customer> retrieveCustomerOfAbranch(Customer customer) {
        
        
        List<Customer> customers = customerDao.insertCustomer(customer);
        
        Result<Customer> customersResult = new Result<>();
        customersResult.setData(customers);
        
        return customersResult;
    }

}
