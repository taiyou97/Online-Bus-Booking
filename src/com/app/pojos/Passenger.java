package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Passenger {
	private Integer id;
	private String name;
	private SexType sex;
	private byte age;
	private Tickets ticketId;

	public Passenger() {
		System.out.println("Passenger()");
	}

	public Passenger(String name, SexType sex, byte age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 30, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase().trim();
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	public SexType getSex() {
		return sex;
	}

	public void setSex(SexType sex) {
		this.sex = sex;
	}

	@Column(nullable = false)
	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	@ManyToOne
	@JoinColumn(name = "ticketId")
	public Tickets getTicketId() {
		return ticketId;
	}

	public void setTicketId(Tickets ticketId) {
		this.ticketId = ticketId;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", Name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}

}
