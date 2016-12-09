package edu.hood.cs.it530.findyourdoctor.physicians;

import java.util.List;

import edu.hood.cs.it530.findyourdoctor.common.beans.Physician;

public class PhysiciansResult {
    
    private List<Physician> data;
    
    private int itemCount;

    public List<Physician> getData() {
        return data;
    }

    public void setData(List<Physician> data) {
        this.data = data;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

}
