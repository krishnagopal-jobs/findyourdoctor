package edu.hood.cs.it530.findyourdoctor.common.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Physician {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Physician [physicianId=");
        builder.append(physicianId);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", middleInitial=");
        builder.append(middleInitial);
        builder.append(", location=");
        builder.append(location);
        builder.append(", specialities=");
        builder.append(specialities);
        builder.append("]");
        return builder.toString();
    }

    private int physicianId;

    private String firstName;

    private String lastName;

    private String middleInitial;

    private Location location;

    private List<Speciality> specialities;

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

}
