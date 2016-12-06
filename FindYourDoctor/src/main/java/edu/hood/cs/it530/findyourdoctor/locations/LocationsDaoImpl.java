package edu.hood.cs.it530.findyourdoctor.locations;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.Location;

/**
 * @author kisna
 *
 */
@Component
public class LocationsDaoImpl extends AbstractDao implements LocationDao {

    public LocationsDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    /* (non-Javadoc)
     * @see edu.hood.cs.it530.findyourdoctor.locations.LocationDao#retrieveLocation(edu.hood.cs.it530.findyourdoctor.common.beans.Location)
     */
    @Override
    public Integer retrieveLocation(Location location)
            throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
        
        namedParameters.put("state", location.getState());
        namedParameters.put("street", location.getStreet());
        namedParameters.put("city", location.getCity());
        namedParameters.put("zip_code", location.getZipCode());
        namedParameters.put("suite_number", location.getSuiteNumber());
        
        
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

        Integer locationId = getNamedParameterJdbcTemplate().queryForObject(locationSearchQuery, namedParameters, Integer.class);
        
        location.setLocationId(locationId);
        
        return locationId;

    }
    
    
    /* (non-Javadoc)
     * @see edu.hood.cs.it530.findyourdoctor.locations.LocationDao#insertLocation(edu.hood.cs.it530.findyourdoctor.common.beans.Location)
     */
    @Override
    @Transactional
    public Location insertLocation(Location location) throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
        
        namedParameters.put("state", location.getState());
        namedParameters.put("street", location.getStreet());
        namedParameters.put("city", location.getCity());
        namedParameters.put("zip_code", location.getZipCode());
        namedParameters.put("suite_number", location.getSuiteNumber());
        namedParameters.put("phone_number", location.getPhoneNumber());
        
        
        String insertLocationStatement = "insert ignore\n"
                + "    into locations\n"
                + "(\n"
                + "    street,\n"
                + "    suite_number,\n"
                + "    city,\n"
                + "    state,\n"
                + "    zip_code,\n"
                + "    phone_number)\n"
                + "VALUES (\n "
                + "   :street, \n"
                + "    :suite_number, \n"
                + "    :city, \n"
                + "    :state, \n"
                + "    :zip_code, \n"
                + "     :phone_number )";

        System.out.println(insertLocationStatement);

        getNamedParameterJdbcTemplate().execute(insertLocationStatement, namedParameters, ps -> ps.executeUpdate());
        
        retrieveLocation(location);
        
        return location;

    }
    
    
}
