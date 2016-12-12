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
public class SpecialitiesDaoImpl extends AbstractDao implements SpecialitiesDao{

    public SpecialitiesDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
    
    /* (non-Javadoc)
     * @see edu.hood.cs.it530.findyourdoctor.specialities.SpecialitiesDao#getSpecialities()
     */
    @Override
    public List<Speciality> getSpecialities()
            throws SQLException {

        Map<String, Object> namedParameters = new HashMap<>();
       
        String specialityRetrievalQuery = "SELECT speciality_id, speciality_name FROM specialities";

        System.out.println(specialityRetrievalQuery);

        List<Speciality> specialities = getNamedParameterJdbcTemplate().query(specialityRetrievalQuery, namedParameters,
                new SpecialityMapper());

        return specialities;
    }
    
    /* (non-Javadoc)
     * @see edu.hood.cs.it530.findyourdoctor.specialities.SpecialitiesDao#getSpecialitiesForAPhysician(int)
     */
    @Override
    public List<Speciality> getSpecialitiesForAPhysician( int physicianId)  {

        String retrieveQualificationsStatement = "SELECT \n"
                + "    s.speciality_name,\n"
                + "    s.speciality_id\n"
                + "FROM\n"
                + "    physicians p\n"
                + "        JOIN\n"
                + "    rln_physician_speciality ps ON p.physician_id = ps.physician_id\n"
                + "    and p.physician_id = :physician_id\n "
                + "               join \n"
                + "        specialities s on s.speciality_id = ps.speciality_id\n        ";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("physician_id", physicianId);

        List<Speciality> specialities =getNamedParameterJdbcTemplate().query(retrieveQualificationsStatement,
                namedParameters, new SpecialityMapper());

        return specialities;
    }
    
    
}
