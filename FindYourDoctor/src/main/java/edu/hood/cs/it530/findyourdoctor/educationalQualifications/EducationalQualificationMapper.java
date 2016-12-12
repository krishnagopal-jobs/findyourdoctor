package edu.hood.cs.it530.findyourdoctor.educationalQualifications;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.hood.cs.it530.findyourdoctor.common.beans.EducationalQualification;

public class EducationalQualificationMapper implements RowMapper<EducationalQualification> {

    @Override
    public EducationalQualification mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

        EducationalQualification educationalQualification = new EducationalQualification();

        educationalQualification.setGraduationDate(resultSet.getDate("graduation_date"));
        educationalQualification.setDegree(resultSet.getString("degree"));
        educationalQualification.setInstituteName(resultSet.getString("institute_name"));
        educationalQualification.setPhysicianId(resultSet.getInt("physician_id"));

        return educationalQualification;
    }

}
