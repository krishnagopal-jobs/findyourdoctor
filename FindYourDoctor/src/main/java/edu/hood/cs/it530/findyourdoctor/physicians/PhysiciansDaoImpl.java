package edu.hood.cs.it530.findyourdoctor.physicians;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;

/**
 * @author kisna
 *
 */
@Component
public class PhysiciansDaoImpl extends AbstractDao implements PhysicianDao {

    public PhysiciansDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    /* (non-Javadoc)
     * @see edu.hood.cs.it530.findyourdoctor.physicians.PhysicianDao#retrievePhysicians(int, int, java.lang.String, java.lang.String)
     */
    @Override
    public List<Physician> retrievePhysicians(int zipCode, int specialityId, String firstName, String lastName)
            throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
        if (zipCode != 0) {
            namedParameters.put("zip_code", zipCode);
        }
        if (specialityId != 0) {
            namedParameters.put("speciality_id", specialityId);
        }

        if (firstName != null && firstName.length() != 0) {
            namedParameters.put("first_name", specialityId);
        }

        if (lastName != null && lastName.length() != 0) {
            namedParameters.put("last_name", lastName);
        }

        String physicianSearchQuery = "";
        physicianSearchQuery += "SELECT \n";
        physicianSearchQuery += "    p.physician_id,\n";
        physicianSearchQuery += "    p.first_name,\n";
        physicianSearchQuery += "    p.last_name,\n";
        physicianSearchQuery += "    p.middle_initial,\n";
        physicianSearchQuery += "    p.location_id,\n";
        physicianSearchQuery += "    l.street,\n";
        physicianSearchQuery += "    l.suite_number,\n";
        physicianSearchQuery += "    l.zip_code,\n";
        physicianSearchQuery += "    l.state,\n";
        physicianSearchQuery += "    l.phone_number\n";
        physicianSearchQuery += "FROM\n";
        physicianSearchQuery += "    physicians p\n";
        physicianSearchQuery += "        JOIN\n";
        physicianSearchQuery += "    locations l ON p.location_id = l.location_id\n";
        if (zipCode != 0) {
            physicianSearchQuery += "        AND l.zip_code = :zip_code\n";
        }
        if (firstName != null && firstName.length() != 0) {
            physicianSearchQuery += "        AND p.first_name LIKE \'%:firstName%\'\n";
        }
        if (lastName != null && lastName.length() != 0) {
            physicianSearchQuery += "        AND p.last_name = \'%:lastName%\'\n";
        }

        physicianSearchQuery += "        JOIN\n    rln_physician_speciality ps ON ps.physician_id = p.physician_id\n";
        if (specialityId != 0) {
            physicianSearchQuery += "        AND ps.speciality_id = :speciality_id\n";
        }
        physicianSearchQuery += "        JOIN\n";
        physicianSearchQuery += "    specialities s ON ps.speciality_id = s.speciality_id\n";

        System.out.println(physicianSearchQuery);

        List<Physician> physicians = getNamedParameterJdbcTemplate().query(physicianSearchQuery, namedParameters,
                new PhysicianMapper());

        return physicians;
    }
    
    
    /* (non-Javadoc)
     * @see edu.hood.cs.it530.findyourdoctor.physicians.PhysicianDao#insertPhysician(edu.hood.cs.it530.findyourdoctor.common.beans.Physician)
     */
    @Override
    public void insertPhysician(Physician physician) {
        
        String insertIntoPhysicianStatement = "";

        insertIntoPhysicianStatement += "INSERT IGNORE INTO physicians \n";
        insertIntoPhysicianStatement += "( \n";
        insertIntoPhysicianStatement += "       first_name, \n";
        insertIntoPhysicianStatement += "       middle_initial, \n";
        insertIntoPhysicianStatement += "       last_name, \n";
        insertIntoPhysicianStatement += "       location_id) \n";
        insertIntoPhysicianStatement += " VALUES \n";
        insertIntoPhysicianStatement += "( \n";
        insertIntoPhysicianStatement += "       :first_name, \n";
        insertIntoPhysicianStatement += "       :middle_initial, \n";
        insertIntoPhysicianStatement += "       :last_name, \n";
        insertIntoPhysicianStatement += "       :location_id \n";
        insertIntoPhysicianStatement +=  ") \n";
        
        Map<String, Object> namedParameters = new HashMap<>();
        
        namedParameters.put("first_name", physician.getFirstName());
        namedParameters.put("middle_initial", physician.getMiddleInitial());
        namedParameters.put("last_name", physician.getLastName());
        namedParameters.put("location_id", physician.getLocation().getLocationId());

        getNamedParameterJdbcTemplate().execute(insertIntoPhysicianStatement, namedParameters, ps -> ps.executeUpdate());
        
    }

}
