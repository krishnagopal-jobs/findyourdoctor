package edu.hood.cs.it530.findyourdoctor.insurances;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.hood.cs.it530.findyourdoctor.common.AbstractDao;
import edu.hood.cs.it530.findyourdoctor.common.beans.Insurance;
import edu.hood.cs.it530.findyourdoctor.common.beans.InsuranceReview;

@Component
public class InsurnaceDaoImpl extends AbstractDao implements InsurancesDao {

    public InsurnaceDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.hood.cs.it530.findyourdoctor.specialities.SpecialitiesDao#
     * getSpecialitiesForAPhysician(int)
     */
    @Override
    public List<InsuranceReview> getInsuranceReviewForAPhysician(int physicianId) {

        String retrieveQualificationsStatement = "SELECT \n"
                + "    i.insurance_id, i.insurance_name, ir.review_date, ir.review_comments, ir.review_ratings\n" + "FROM\n"
                + "    insurance_reviews ir\n" + "        JOIN\n"
                + "    insurances i ON ir.insurance_id = i.insurance_id\n" + "WHERE\n"
                + "    ir.physician_id = :physician_id";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("physician_id", physicianId);

        List<InsuranceReview> insuranceReviews = getNamedParameterJdbcTemplate().query(retrieveQualificationsStatement,
                namedParameters, new InsuranceReviewMapper());

        return insuranceReviews;
    }

    @Override
    public List<Insurance> retrieveAcceptedInsurancesByAPhysician(int physicianId) {

        String retrieveQualificationsStatement = "SELECT \n" + "    i.insurance_id, i.insurance_name\n" + "FROM\n"
                + "    accepted_insurances ia\n" + "        JOIN\n"
                + "    insurances i ON ia.insurance_id = i.insurance_id\n" + "WHERE\n"
                + "    ia.physician_id = :physician_id";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("physician_id", physicianId);

        List<Insurance> acceptedInsurances = getNamedParameterJdbcTemplate().query(retrieveQualificationsStatement,
                namedParameters, new InsuranceMapper());

        return acceptedInsurances;
    }

}
