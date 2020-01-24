package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bus {
	private Integer id;
	private String busNo;
	private byte capacity;
	private BusType type;
	private Date arrival;
	private Date destination;
	private float price;
	private Routes routeId;
	private Frequency frequency;
	private List<Seats> seats = new ArrayList<Seats>();
	private List<Tickets> tickets = new ArrayList<Tickets>();
	private List<Feedback> feedbacks = new ArrayList<>();
	
	/*
	INSERT INTO Bus (busNo, capacity, type, arrival, destination, price, routeId) VALUES ('MH 04 HU 2363', 20, 'SEATER_AC', '06:30:00', '20:30:00', 850.00, 1);
	INSERT INTO Bus (busNo, capacity, type, arrival, destination, price, routeId) VALUES ('MH 04 HU 2364', 20, 'SEATER_AC', '07:30:00', '20:30:00', 750.00, 1);
	INSERT INTO Bus (busNo, capacity, type, arrival, destination, price, routeId) VALUES ('MH 04 HU 2354', 20, 'SEATER_AC', '09:30:00', '23:30:00', 750.00, 3);
	INSERT INTO Bus (busNo, capacity, type, arrival, destination, price, routeId) VALUES ('MH 04 HU 2344', 20, 'SEATER_NON_AC', '13:30:00', '01:30:00', 550.00, 4);
	*/
	
	public Bus() {
		System.out.println("Bus()");
	}

	public Bus(String busNo, byte capacity, BusType type, Date arrival, Date destination, float price, Frequency frequency) {
		super();
		this.setBusNo(busNo);
		this.capacity = capacity;
		this.type = type;
		this.arrival = arrival;
		this.destination = destination;
		this.price = price;
		this.frequency = frequency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 13, nullable = false)
	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo.toUpperCase().trim();
	}

	@Column(nullable = false)
	public byte getCapacity() {
		return capacity;
	}

	public void setCapacity(byte capacity) {
		this.capacity = capacity;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	public BusType getType() {
		return type;
	}

	public void setType(BusType type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm", timezone = "Asia/Kolkata")
	@Column(nullable = false)
	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm", timezone = "Asia/Kolkata")
	@Column(nullable = false)
	public Date getDestination() {
		return destination;
	}

	public void setDestination(Date destination) {
		this.destination = destination;
	}

	@Column(nullable = false)
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@ManyToOne
	@JoinColumn(name = "routeId")
	public Routes getRouteId() {
		return routeId;
	}

	public void setRouteId(Routes routeId) {
		this.routeId = routeId;
	}

	@OneToOne(mappedBy = "busId", cascade = CascadeType.ALL, orphanRemoval = true)
	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	
	public void addFrequency(Frequency f) {
		this.frequency = f;
		f.setBusId(this);
	}
	
	public void removeFrequency(Frequency f) {
		this.frequency = null;
		f.setBusId(null);
	}

	@JsonIgnore
	@OneToMany(mappedBy = "busId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Seats> getSeats() {
		return seats;
	}

	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}
	
	public void addSeats(Seats seats) {
		this.seats.add(seats);
		seats.setBusId(this);
	}
	
	public void removeSeats(Seats seats) {
		this.seats.remove(seats);
		seats.setBusId(null);
	}

	@JsonIgnore
	@OneToMany(mappedBy = "busId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}
	
	public void addTickets(Tickets tickets) {
		this.tickets.add(tickets);
		tickets.setBusId(this);
	}
	
	public void removeTickets(Tickets tickets) {
		this.tickets.remove(tickets);
		tickets.setBusId(null);
	}

	@JsonIgnore
	@OneToMany(mappedBy = "busId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	
	public void addFeedback(Feedback f) {
		this.feedbacks.add(f);
		f.setBusId(this);
	}
	
	public void removeFeedback(Feedback f) {
		this.feedbacks.remove(f);
		f.setBusId(null);
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", capacity=" + capacity + ", type=" + type + ", arrival=" + arrival + ", destination="
				+ destination + ", price=" + price + "]";
	}

}
