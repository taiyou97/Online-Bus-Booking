package com.app.pojos;

import java.time.LocalDate;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tickets {
	private Integer id;
	private LocalDate bookedDate;
	private String ownerName;
	private Long ownerPhone;
	private double fare;
	private byte noOfSeats;
	private PaymentType paymentMode;
	private Bus busId;
	private User userId;
	private List<Passenger> passengers = new ArrayList<Passenger>();

	Tickets() {
		System.out.println("Tickets()");
	}

	public Tickets(LocalDate bookedDate, String ownerName, Long ownerPhone, double fare, byte noOfSeats,
			PaymentType paymentMode) {
		super();
		this.bookedDate = bookedDate;
		this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
		this.fare = fare;
		this.noOfSeats = noOfSeats;
		this.paymentMode = paymentMode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public LocalDate getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}

	@Column(length = 30, nullable = false)
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName.toUpperCase().trim();
	}

	@Column(nullable = false)
	public Long getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(Long ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	@Column(nullable = false)
	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Column(nullable = false)
	public byte getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(byte noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public PaymentType getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentType paymentMode) {
		this.paymentMode = paymentMode;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "busId")
	public Bus getBusId() {
		return busId;
	}

	public void setBusId(Bus busId) {
		this.busId = busId;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "ticketId", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	public void addPasseneger(Passenger p) {
		this.passengers.add(p);
		p.setTicketId(this);
	}
	
	public void removePasseneger(Passenger p) {
		this.passengers.remove(p);
		p.setTicketId(null);
	}

	@Override
	public String toString() {
		return "Tickets [id=" + id + ", bookedDate=" + bookedDate + ", ownerName=" + ownerName + ", ownerPhone="
				+ ownerPhone + ", fare=" + fare + ", noOfSeats=" + noOfSeats + ", paymentMode=" + paymentMode + "]";
	}

}
