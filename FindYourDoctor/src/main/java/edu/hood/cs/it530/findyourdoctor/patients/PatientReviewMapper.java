package edu.hood.cs.it530.findyourdoctor.patients;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.hood.cs.it530.findyourdoctor.common.beans.PatientReview;

public class PatientReviewMapper implements RowMapper<PatientReview> {

    @Override
    public PatientReview mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

        PatientReview patientReview = new PatientReview();
        patientReview.setReviewDate(resultSet.getDate("review_date"));
        patientReview.setRatings(resultSet.getInt("review_ratings"));
        patientReview.setComments(resultSet.getString("review_comments"));
        patientReview.setPatientName(resultSet.getString("patient_name"));
        return patientReview;
    }

}
