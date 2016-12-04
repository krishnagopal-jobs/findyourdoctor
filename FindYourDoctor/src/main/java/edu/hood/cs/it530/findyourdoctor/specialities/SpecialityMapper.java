package edu.hood.cs.it530.findyourdoctor.specialities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.hood.cs.it530.findyourdoctor.common.beans.Speciality;

public class SpecialityMapper implements RowMapper<Speciality> {

    @Override
    public Speciality mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

        Speciality speciality = new Speciality();

        speciality.setSpecialityId(resultSet.getInt("speciality_id"));
        speciality.setSpecialityName(resultSet.getString("speciality_name"));

        return speciality;
    }

}
