package edu.hood.cs.it530.findyourdoctor.common.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Physician {

    private int physicianId;

    private String firstName;

    private String lastName;

    private String middleInitial;

    private Location location;

    private List<Speciality> specialities;

    private List<EducationalQualification> educationalQualifications;

    private List<InsuranceReview> insuranceReviews;

    private List<Insurance> acceptedInsurances;

    private List<PatientReview> patientReviews;

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public List<Speciality> getSpecialities() {
        if (specialities == null) {
            specialities = new ArrayList<>();
        }
        return specialities;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            firstName = firstName.trim();
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            lastName = lastName.trim();
        }
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        if (middleInitial != null) {
            middleInitial = middleInitial.trim();
        }
        this.middleInitial = middleInitial;
    }

    public List<EducationalQualification> getEducationalQualifications() {
        return educationalQualifications;
    }

    public void setEducationalQualifications(List<EducationalQualification> educationalQualifications) {
        this.educationalQualifications = educationalQualifications;
    }

    public void setInsuranceReviews(List<InsuranceReview> insuranceReviews) {
        this.insuranceReviews = insuranceReviews;
    }

    /**
     * @return the insuranceReviews
     */
    public List<InsuranceReview> getInsuranceReviews() {
        return insuranceReviews;
    }

    /**
     * @return the acceptedInsurances
     */
    public List<Insurance> getAcceptedInsurances() {
        return acceptedInsurances;
    }

    /**
     * @param acceptedInsurances
     *            the acceptedInsurances to set
     */
    public void setAcceptedInsurances(List<Insurance> acceptedInsurances) {
        this.acceptedInsurances = acceptedInsurances;
    }

    /**
     * @param patientReviews
     */
    public void setPatientReviews(List<PatientReview> patientReviews) {
        this.patientReviews = patientReviews;
    }

    /**
     * @return the patientReviews
     */
    public List<PatientReview> getPatientReviews() {
        return patientReviews;
    }

}
