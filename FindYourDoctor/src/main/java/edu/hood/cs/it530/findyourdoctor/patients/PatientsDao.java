package edu.hood.cs.it530.findyourdoctor.patients;

import java.util.List;

import edu.hood.cs.it530.findyourdoctor.common.beans.PatientReview;

public interface PatientsDao {


    List<PatientReview> retrievePatientReviewForAPhysician(int physicianId);
    

}