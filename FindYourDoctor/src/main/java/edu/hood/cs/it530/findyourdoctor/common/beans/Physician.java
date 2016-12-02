package edu.hood.cs.it530.findyourdoctor.common.beans;

import java.util.ArrayList;
import java.util.List;

public class Physician {

	private int physicianId;

	private String firstName;

	private String lastName;

	private String middleInitial;

	private Location location;
	
	private List<Speciality> specialities;

	public List<Speciality> getSpecialities() {
		if(specialities == null) {
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
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

}
