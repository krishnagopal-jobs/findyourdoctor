package edu.hood.cs.it530.findyourdoctor.searchphysician;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;

/**
 * @author kisna
 *
 */
@Component
public class SearchPhysicianDao extends AbstractDao {

    @Autowired
    public SearchPhysicianDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public List<Physician> getPhysicians(int zipCode, int specialityId, String firstName, String lastName)
            throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
        if (zipCode != 0) {
            namedParameters.put("zipCode", zipCode);
        }
        if (specialityId != 0) {
            namedParameters.put("specialityId", specialityId);
        }

        if (firstName != null && firstName.length() != 0) {
            namedParameters.put("firstName", specialityId);
        }

        if (lastName != null && lastName.length() != 0) {
            namedParameters.put("lastName", lastName);
        }

        // @formatter:off
        String physicianSearchQuery = "SELECT \n"
                + "    p.physician_id,\n"
                + "    p.first_name,\n"
                + "    p.last_name,\n"
                + "    p.middle_initial,\n"
                + "    p.location_id,\n"
                + "    l.street,\n"
                + "    l.suite_number,\n"
                + "    l.zip_code,\n"
                + "    l.state,\n"
                + "    l.phone_number\n"
                + "FROM\n"
                + "    physicians p\n"
                + "        JOIN\n"
                + "    locations l ON p.location_id = l.location_id\n"
                + "        JOIN\n"
                + "    rln_physician_speciality ps ON ps.physician_id = p.physician_id\n"
                + "        JOIN\n    specialities s ON ps.speciality_id = s.speciality_id\n"
                + "WHERE\n"
                + "    1 = 1 \n";
         // @formatter:on
        
        if (zipCode != 0) {
            physicianSearchQuery += "                 AND l.zip_code = :zipCode \n";
        }
        
        if (specialityId != 0) {
            physicianSearchQuery += "           AND s.speciality_id = :specialityId\n";
        }

        if (firstName != null && firstName.length() != 0) {
            physicianSearchQuery += "           AND p.first_name like '%:firstName%'\n";
        }

        if (lastName != null && lastName.length() != 0) {
            physicianSearchQuery += "          AND p.last_name = '%:lastName%'\n";
        }

        System.out.println(physicianSearchQuery);

        List<Physician> physicians = getNamedParameterJdbcTemplate().query(physicianSearchQuery, namedParameters,
                new PhysicianMapper());

        return physicians;
    }

}
