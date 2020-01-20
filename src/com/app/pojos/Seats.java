package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seats {
	private Integer id;
	private LocalDate bookDate;
	private byte availableSeats;
	private Bus busId;

	public Seats() {
		System.out.println("Seats()");
	}

	public Seats(LocalDate bookDate, byte availableSeats) {
		super();
		this.bookDate = bookDate;
		this.availableSeats = availableSeats;
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
	public LocalDate getBookDate() {
		return bookDate;
	}

	public void setBookDate(LocalDate bookDate) {
		this.bookDate = bookDate;
	}

	@Column(nullable = false)
	public byte getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(byte availableSeats) {
		this.availableSeats = availableSeats;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "busId")
	public Bus getBusId() {
		return busId;
	}

	public void setBusId(Bus busId) {
		this.busId = busId;
	}

	@Override
	public String toString() {
		return "Seats [id=" + id + ", bookDate=" + bookDate + ", availableSeats=" + availableSeats + "]";
	}

}
