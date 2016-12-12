package edu.hood.cs.it530.findyourdoctor.educationalQualifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.EducationalQualification;

@Component
public class EducationalQualificationsDaoImpl extends AbstractDao implements EducationalQualificationsDao {

    public EducationalQualificationsDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.hood.cs.it530.findyourdoctor.specialities.SpecialitiesDao#
     * getSpecialitiesForAPhysician(int)
     */
    @Override
    public List<EducationalQualification> getEducationalQualificationsForAPhysician(int physicianId) {

        String retrieveQualificationsStatement = "SELECT \n"
                + "    graduation_date, physician_id, degree, institute_name\n" + "FROM\n"
                + "    educational_qualifications\n" + "WHERE\n" + "    physician_id = :physician_id";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("physician_id", physicianId);

        List<EducationalQualification> specialities = getNamedParameterJdbcTemplate()
                .query(retrieveQualificationsStatement, namedParameters, new EducationalQualificationMapper());

        return specialities;
    }

}
