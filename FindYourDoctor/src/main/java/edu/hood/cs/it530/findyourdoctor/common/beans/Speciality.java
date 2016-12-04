package edu.hood.cs.it530.findyourdoctor.common.beans;

public class Speciality {

    private int specialityId;

    private String specialityName;

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int speciality_id) {
        this.specialityId = speciality_id;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String speciality_name) {
        this.specialityName = speciality_name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + specialityId;
        result = prime * result + ((specialityName == null) ? 0 : specialityName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Speciality other = (Speciality) obj;
        if (specialityId != other.specialityId)
            return false;
        if (specialityName == null) {
            if (other.specialityName != null)
                return false;
        } else if (!specialityName.equals(other.specialityName))
            return false;
        return true;
    }

}
