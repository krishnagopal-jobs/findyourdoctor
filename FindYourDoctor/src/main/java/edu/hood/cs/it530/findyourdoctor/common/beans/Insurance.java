package edu.hood.cs.it530.findyourdoctor.common.beans;

public class Insurance {

    private int insuranceId;
    
    private String insuranceName;

    /**
     * @return the insuranceId
     */
    public int getInsuranceId() {
        return insuranceId;
    }

    /**
     * @param insuranceId the insuranceId to set
     */
    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    /**
     * @return the insuranceName
     */
    public String getInsuranceName() {
        return insuranceName;
    }

    /**
     * @param insuranceName the insuranceName to set
     */
    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
    
}
