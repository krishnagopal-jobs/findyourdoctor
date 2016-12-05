package edu.hood.cs.it530.findyourdoctor.locations;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.Location;

/**
 * @author kisna
 *
 */
@Component
public class LocationsDao extends AbstractDao {

    public LocationsDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Integer retrieveLocation(Location location)
            throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
        
        namedParameters.put("state", location.getState());
        namedParameters.put("street", location.getStreet());
        namedParameters.put("city", location.getCity());
        namedParameters.put("zip_code", location.getZipCode());
        
        
        String locationSearchQuery = "SELECT \n"
                + "    location_id\n"
                + "FROM\n"
                + "    locations\n"
                + "WHERE\n "
                + "   state = :state \n"
                + "         AND city = :city \n"
                + "         AND street = :street \n"
                + "        AND suite_number = :suite_number\n"
                + "        AND zip_code = :zip_code";
        

        System.out.println(locationSearchQuery);

        return getNamedParameterJdbcTemplate().queryForObject(locationSearchQuery, namedParameters, Integer.class);

    }

    public Location insertLocation(Location location) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
