package com.app.dao;

import java.time.LocalDate;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Bus;
import com.app.pojos.DayFromDate;
import com.app.pojos.Seats;

@Repository
public class SeatsDaoImpl implements ISeatsDao{

	@Autowired
	SessionFactory sf;
	
	@Override
	public Seats getSeatsByBusId(int busId, String date) {
		String jpql = "select s from Seats s where s.busId = :busId and s.bookDate = :date";
		Bus b = sf.getCurrentSession().get(Bus.class, busId);
		LocalDate d = DayFromDate.getLocalDate(date);
		Seats s;
		try {
		s = sf.getCurrentSession().createQuery(jpql, Seats.class).setParameter("busId", b).setParameter("date", d).getSingleResult();
		}catch (NoResultException e) {
			s = new Seats(d, b.getCapacity());
			b.addSeats(s);
			sf.getCurrentSession().merge(b);
		}
		return s;
	}

	@Override
	public String bookSeatsByBus(int busId, String date, int noOfSeats) {
		String jpql = "select s from Seats s where s.busId = :busId and s.bookDate = :date";
		Bus b = sf.getCurrentSession().get(Bus.class, busId);
		LocalDate d = DayFromDate.getLocalDate(date);
		Seats s = sf.getCurrentSession().createQuery(jpql, Seats.class).setParameter("busId", b).setParameter("date", d).getSingleResult();
		if( noOfSeats < s.getAvailableSeats() )
			s.setAvailableSeats((byte)(s.getAvailableSeats() - noOfSeats));
		sf.getCurrentSession().update(s);
		return "Seats booked successfully";
	}

	@Override
	public String addSeatsByBus(int busId, String date, int noOfSeats) {
		String jpql = "select s from Seats s where s.busId = :busId and s.bookDate = :date";
		Bus b = sf.getCurrentSession().get(Bus.class, busId);
		LocalDate d = DayFromDate.getLocalDate(date);
		Seats s = sf.getCurrentSession().createQuery(jpql, Seats.class).setParameter("busId", b).setParameter("date", d).getSingleResult();
		if( noOfSeats < s.getAvailableSeats() )
			s.setAvailableSeats((byte)(s.getAvailableSeats() + noOfSeats));
		sf.getCurrentSession().update(s);
		return "Seats added successfully";
	}

	@Override
	public String removeSeatsByBus(int busId, String date) {
		String jpql = "select s from Seats s where s.busId = :busId and s.bookDate = :date";
		Bus b = sf.getCurrentSession().get(Bus.class, busId);
		LocalDate d = DayFromDate.getLocalDate(date);
		Seats s = sf.getCurrentSession().createQuery(jpql, Seats.class).setParameter("busId", b).setParameter("date", d).getSingleResult();
		b.removeSeats(s);
		sf.getCurrentSession().merge(s);
		return "Seats removed successfully";
	}

}
