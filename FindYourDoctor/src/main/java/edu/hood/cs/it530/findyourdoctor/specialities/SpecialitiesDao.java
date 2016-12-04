package edu.hood.cs.it530.findyourdoctor.specialities;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.Speciality;

@Component
public class SpecialitiesDao extends AbstractDao{

    public SpecialitiesDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
    
    public List<Speciality> getSpecialities()
            throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
       
        String specialityRetrievalQuery = "SELECT speciality_id, speciality_name FROM specialities";

        System.out.println(specialityRetrievalQuery);

        List<Speciality> specialities = getNamedParameterJdbcTemplate().query(specialityRetrievalQuery, namedParameters,
                new SpecialityMapper());

        return specialities;
    }
    
}
