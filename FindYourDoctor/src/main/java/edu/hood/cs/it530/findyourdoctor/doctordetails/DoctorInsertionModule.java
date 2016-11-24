/**
 * 
 */
package edu.hood.cs.it530.findyourdoctor.doctordetails;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author kisna
 *
 */
@Component
public class DoctorInsertionModule {
	
	private final JdbcTemplate jdbcTemplate;
	
    @Autowired
    public DoctorInsertionModule(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    public void getDetails() {
    	
    	jdbcTemplate.execute("create table mytable (id integer, name varchar(100))");
    	
    }
    
    

}
