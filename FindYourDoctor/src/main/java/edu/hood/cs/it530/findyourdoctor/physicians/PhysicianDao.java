package edu.hood.cs.it530.findyourdoctor.physicians;

import java.sql.SQLException;
import java.util.List;

import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;

public interface PhysicianDao {

    List<Physician> retrievePhysicians(int zipCode, int specialityId, String firstName, String lastName)
            throws SQLException;

    void insertPhysician(Physician physician);

}