package edu.hood.cs.it530.findyourdoctor.patients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.PatientReview;

@Component
public class PatientsDaoImpl extends AbstractDao implements PatientsDao {

    public PatientsDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    @Override
    public List<PatientReview> retrievePatientReviewForAPhysician(int physicianId) {


        String retrievePatientReviewQuery = "SELECT \n"
                + "    patient_name, date review_date, ratings review_ratings, review_content review_comments\n"
                + "FROM\n"
                + "    patient_reviews \n"
                + "WHERE\n"
                + "    physician_id = :physician_id";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("physician_id", physicianId);

        List<PatientReview> patientReviews = getNamedParameterJdbcTemplate().query(retrievePatientReviewQuery,
                namedParameters, new PatientReviewMapper());

        return patientReviews;
    
    }

}
