package edu.hood.cs.it530.findyourdoctor.insurances;

import java.util.List;

import edu.hood.cs.it530.findyourdoctor.common.beans.Insurance;
import edu.hood.cs.it530.findyourdoctor.common.beans.InsuranceReview;

public interface InsurancesDao {


    List<InsuranceReview> getInsuranceReviewForAPhysician(int physicianId);
    
    List<Insurance> retrieveAcceptedInsurancesByAPhysician(int physicianId);

}