package edu.hood.cs.it530.findyourdoctor.physicians;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.hood.cs.it530.findyourdoctor.common.beans.Location;
import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;

public class PhysicianMapper implements RowMapper<Physician> {

	@Override
	public Physician mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

		Physician physician = new Physician();

		physician.setPhysicianId(resultSet.getInt("physician_id"));
		physician.setFirstName(resultSet.getString("first_name"));
		physician.setLastName(resultSet.getString("last_name"));
		physician.setMiddleInitial(resultSet.getString("middle_initial"));
		
		Location location = new Location();
		physician.setLocation(location);
		location.setLocationId(resultSet.getInt("location_id"));

		return physician;
	}

}
