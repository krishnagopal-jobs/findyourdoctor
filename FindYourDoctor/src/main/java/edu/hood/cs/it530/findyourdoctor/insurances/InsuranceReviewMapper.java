package edu.hood.cs.it530.findyourdoctor.insurances;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.hood.cs.it530.findyourdoctor.common.beans.Insurance;
import edu.hood.cs.it530.findyourdoctor.common.beans.InsuranceReview;

public class InsuranceReviewMapper implements RowMapper<InsuranceReview> {

    @Override
    public InsuranceReview mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

        InsuranceReview insuranceReview = new InsuranceReview();

        insuranceReview.setReviewDate(resultSet.getDate("review_date"));
        insuranceReview.setRatings(resultSet.getInt("review_ratings"));
        insuranceReview.setComments(resultSet.getString("review_comments"));
        
        Insurance insurance = new Insurance();
        insurance.setInsuranceName(resultSet.getString("insurance_name"));
        insurance.setInsuranceId(resultSet.getInt("insurance_id"));
        
        insuranceReview.setInsurance(insurance);

        return insuranceReview;
    }

}
