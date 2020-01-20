package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Routes {
	private Integer id;
	private String source;
	private StateType sourceState;
	private String destination;
	private StateType destinationState;
	private List<Bus> buses = new ArrayList<>();
	
	/*
	INSERT INTO Routes (source, sourceState, destination, destinationState) VALUES ('PUNE', 'MAHARASHTRA', 'BANGALORE', 'KARNATAKA');
	INSERT INTO Routes (source, sourceState, destination, destinationState) VALUES ('PUNE', 'MAHARASHTRA', 'NAGPUR', 'MAHARASHTRA');
	INSERT INTO Routes (source, sourceState, destination, destinationState) VALUES ('NAGPUR', 'MAHARASHTRA', 'PUNE', 'MAHARASHTRA');
	INSERT INTO Routes (source, sourceState, destination, destinationState) VALUES ('BANGALORE', 'KARNATAKA', 'PUNE', 'MAHARASHTRA');
	INSERT INTO Routes (source, sourceState, destination, destinationState) VALUES ('NAGPUR', 'MAHARASHTRA', 'BANGALORE', 'KARNATAKA');
	INSERT INTO Routes (source, sourceState, destination, destinationState) VALUES ('MUMBAI', 'MAHARASHTRA', 'BANGALORE', 'KARNATAKA');
	*/
	
	public Routes() {
		System.out.println("Routes()");
	}

	public Routes(String source, StateType sourceState, String destination, StateType destinationState) {
		super();
		this.source = source.toUpperCase().trim();
		this.sourceState = sourceState;
		this.destination = destination.toUpperCase().trim();
		this.destinationState = destinationState;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 25, nullable = false)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source.toUpperCase().trim();
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 25, nullable = false)
	public StateType getSourceState() {
		return sourceState;
	}

	public void setSourceState(StateType sourceState) {
		this.sourceState = sourceState;
	}

	@Column(length = 25, nullable = false)
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination.toUpperCase().trim();
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 25, nullable = false)
	public StateType getDestinationState() {
		return destinationState;
	}

	public void setDestinationState(StateType destinationState) {
		this.destinationState = destinationState;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "routeId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Bus> getBuses() {
		return buses;
	}

	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}
	
	public void addBuses(Bus bus) {
		this.buses.add(bus);
		bus.setRouteId(this);
	}
	
	public void removeBuses(Bus bus) {
		this.buses.remove(bus);
		bus.setRouteId(null);
	}

	@Override
	public String toString() {
		return "Routes [id=" + id + ", source=" + source + ", destination=" + destination + "]";
	}

}
