package edu.hood.cs.it530.findyourdoctor.locations;

import java.sql.SQLException;

import edu.hood.cs.it530.findyourdoctor.common.beans.Location;

public interface LocationDao {

    Integer retrieveLocation(Location location) throws SQLException;

    Location insertLocation(Location location) throws SQLException;

}