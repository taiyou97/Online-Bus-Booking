package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Frequency {
	private Integer id;
	private boolean sun;
	private boolean mon;
	private boolean tues;
	private boolean wed;
	private boolean thurs;
	private boolean fri;
	private boolean sat;
	private Bus busId;
	
	/*
	INSERT INTO Frequency (sun, mon, tues, wed, thurs, fri, sat, busId) VALUES (1, 1, 1, 1, 1, 1, 1, 1);
	INSERT INTO Frequency (sun, mon, tues, wed, thurs, fri, sat, busId) VALUES (true, true, true, false, true, false, true, 2);
	INSERT INTO Frequency (sun, mon, tues, wed, thurs, fri, sat, busId) VALUES (false, true, false, true, true, true, true, 3);
	INSERT INTO Frequency (sun, mon, tues, wed, thurs, fri, sat, busId) VALUES (true, true, true, true, true, true, true, 4);
	INSERT INTO Frequency (sun, mon, tues, wed, thurs, fri, sat, busId) VALUES (true, true, true, true, true, true, true, 6);
	INSERT INTO Frequency (sun, mon, tues, wed, thurs, fri, sat, busId) VALUES (true, true, true, true, true, true, true, 7);
	*/
	
	public Frequency() {
		System.out.println("Frequency()");
	}

	public Frequency(boolean sun, boolean mon, boolean tues, boolean wed, boolean thurs, boolean fri, boolean sat) {
		super();
		this.sun = sun;
		this.mon = mon;
		this.tues = tues;
		this.wed = wed;
		this.thurs = thurs;
		this.fri = fri;
		this.sat = sat;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isSun() {
		return sun;
	}

	public void setSun(boolean sun) {
		this.sun = sun;
	}

	public boolean isMon() {
		return mon;
	}

	public void setMon(boolean mon) {
		this.mon = mon;
	}

	public boolean isTues() {
		return tues;
	}

	public void setTues(boolean tues) {
		this.tues = tues;
	}

	public boolean isWed() {
		return wed;
	}

	public void setWed(boolean wed) {
		this.wed = wed;
	}

	public boolean isThurs() {
		return thurs;
	}

	public void setThurs(boolean thurs) {
		this.thurs = thurs;
	}

	public boolean isFri() {
		return fri;
	}

	public void setFri(boolean fri) {
		this.fri = fri;
	}

	public boolean isSat() {
		return sat;
	}

	public void setSat(boolean sat) {
		this.sat = sat;
	}

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "busId")
	public Bus getBusId() {
		return busId;
	}

	public void setBusId(Bus busId) {
		this.busId = busId;
	}
	
	public boolean isRuns(int day) {
		if( day==0 )
			return this.sun;
		if(day==1)
			return this.mon;
		if(day==2)
			return this.tues;
		if(day==3)
			return this.wed;
		if(day==4)
			return this.thurs;
		if(day==5)
			return this.fri;
		if(day==6)
			return this.sat;
		return false;
	}

	@Override
	public String toString() {
		return "Frequency [id=" + id + ", sun=" + sun + ", mon=" + mon + ", tues=" + tues + ", wed=" + wed + ", thurs="
				+ thurs + ", fri=" + fri + ", sat=" + sat + "]";
	}

}
