package edu.hood.cs.it530.findyourdoctor.searchphysician;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;

/**
 * @author kisna
 *
 */
@Component
public class SearchPhysicianDao {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public SearchPhysicianDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        DataSource dataSource = jdbcTemplate.getDataSource();
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Physician> getPhysicians(int zipCode, int specialityId, String firstName, String lastName)
            throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("zipCode", zipCode);
        namedParameters.put("specialityId", specialityId);
        namedParameters.put("firstName", specialityId);
        namedParameters.put("lastName", specialityId);

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
                + "    1 = 1 \n"
                + "			AND l.zip_code = :zipCode \n"
                + "	        AND s.speciality_id = :specialityId\n"
                + "	        AND p.first_name like '%:firstName%'\n"
                + "	        AND p.last_name = '%:lastName%'\n";
        // @formatter:on

        System.out.println(physicianSearchQuery);
        
        List<Physician> physicians = namedParameterJdbcTemplate.query(physicianSearchQuery, namedParameters,
                new PhysicianMapper());

        return physicians;
    }

}
