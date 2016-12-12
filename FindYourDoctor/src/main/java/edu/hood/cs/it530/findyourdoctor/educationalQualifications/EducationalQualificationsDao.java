package edu.hood.cs.it530.findyourdoctor.educationalQualifications;

import java.util.List;

import edu.hood.cs.it530.findyourdoctor.common.beans.EducationalQualification;

public interface EducationalQualificationsDao {


    List<EducationalQualification> getEducationalQualificationsForAPhysician(int physicianId);

}