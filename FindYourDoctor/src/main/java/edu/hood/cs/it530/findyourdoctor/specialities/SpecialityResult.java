package edu.hood.cs.it530.findyourdoctor.specialities;

import java.util.List;

import edu.hood.cs.it530.findyourdoctor.common.beans.Speciality;

public class SpecialityResult {
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
        result = prime * result + ((specialities == null) ? 0 : specialities.hashCode());
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
        SpecialityResult other = (SpecialityResult) obj;
        if (count != other.count)
            return false;
        if (specialities == null) {
            if (other.specialities != null)
                return false;
        } else if (!specialities.equals(other.specialities))
            return false;
        return true;
    }

    private int count;
    
    private List<Speciality> specialities;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

}
