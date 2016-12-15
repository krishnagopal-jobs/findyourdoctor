package edu.hood.cs.it530.lab8.customers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("customers")
@Component
public class CustomerResource {

    Logger logger = LoggerFactory.getLogger(CustomerResource.class);
    
    @Autowired
    private CustomerDao customerDao;


    @GET
    @Produces({ "application/json" })
    @Path("/branches/{branchName}")
    public List<Customer> retrieveCustomerOfAbranch(@PathParam("branchName") String branchName) {

        List<Customer> customers = customerDao.getCustomersForABrach(branchName);

        return customers;
    }

}
