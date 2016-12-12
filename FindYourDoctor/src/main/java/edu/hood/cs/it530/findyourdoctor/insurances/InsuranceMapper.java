package edu.hood.cs.it530.findyourdoctor.insurances;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.hood.cs.it530.findyourdoctor.common.beans.Insurance;

public class InsuranceMapper implements RowMapper<Insurance> {

    @Override
    public Insurance mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

        Insurance insurance = new Insurance();
        insurance.setInsuranceName(resultSet.getString("insurance_name"));
        insurance.setInsuranceId(resultSet.getInt("insurance_id"));
        

        return insurance;
    }

}
