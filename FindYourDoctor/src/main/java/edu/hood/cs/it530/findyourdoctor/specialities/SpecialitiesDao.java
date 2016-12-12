package edu.hood.cs.it530.findyourdoctor.specialities;

import java.sql.SQLException;
import java.util.List;

import edu.hood.cs.it530.findyourdoctor.common.beans.Speciality;

public interface SpecialitiesDao {

    List<Speciality> getSpecialities() throws SQLException;

    List<Speciality> getSpecialitiesForAPhysician(int physicianId);

}