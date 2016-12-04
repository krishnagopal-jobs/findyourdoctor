package edu.hood.cs.it530.findyourdoctor.physicians;

import java.util.List;

import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;

public class PhysiciansResult {
    
    private List<Physician> physicians;
    
    private int count;

    public List<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
