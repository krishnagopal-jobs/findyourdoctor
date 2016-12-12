package edu.hood.cs.it530.findyourdoctor.common.beans;

import java.util.Date;

public class EducationalQualification {
    
    @Override
    public String toString() {
        return "EducationalQualification [graduationDate=" + graduationDate + ", instituteName=" + instituteName
                + ", degree=" + degree + ", physicianId=" + physicianId + "]";
    }

    private Date graduationDate;
    
    private String instituteName;
    
    private String degree;
    
    private int physicianId;

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduatedDate) {
        this.graduationDate = graduatedDate;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

}
