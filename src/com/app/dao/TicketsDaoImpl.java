package com.app.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Bus;
import com.app.pojos.DayFromDate;
import com.app.pojos.Tickets;
import com.app.pojos.User;

@Repository
public class TicketsDaoImpl implements ITicketsDao {

	@Autowired
	private SessionFactory sf;
	
	@Autowired
	private ISeatsDao seatsDao;
	
	@Override
	public Tickets bookTickets(Tickets t, int busId, int customerId) {
		Bus b = sf.getCurrentSession().get(Bus.class, busId);
		User u = sf.getCurrentSession().get(User.class, customerId);
		b.addTickets(t);
		u.addTickets(t);
		seatsDao.bookSeatsByBus(busId, DayFromDate.getStringDate(t.getBookedDate()), t.getNoOfSeats());
		sf.getCurrentSession().merge(b);
		sf.getCurrentSession().merge(u);
		return t;
	}

	@Override
	public Tickets cancelTickets(int ticketId) {
		Tickets t = sf.getCurrentSession().get(Tickets.class, ticketId);
		Bus b = sf.getCurrentSession().get(Bus.class, t.getBusId().getId());
		seatsDao.addSeatsByBus(b.getId(), t.getBookedDate().toString(), t.getNoOfSeats());
		t.setNoOfSeats((byte)0);
		sf.getCurrentSession().saveOrUpdate(t);
		sf.getCurrentSession().saveOrUpdate(b);
		return null;
	}

	@Override
	public List<Tickets> getTicketsofUser(int userId) {
		String jpql = "select t from Tickets t where t.userId = :user";
		User u = sf.getCurrentSession().get(User.class, userId);
		List<Tickets> list = sf.getCurrentSession().createQuery(jpql, Tickets.class).setParameter("user", u).getResultList();
		return list;
	}

	@Override
	public List<Tickets> getTicketsofBus(int busId, String date) {
		String jpql = "select t from Tickets t where t.busId = :bus and t.bookedDate = :date";
		Bus b = sf.getCurrentSession().get(Bus.class, busId);
		Date d = DayFromDate.getDate(date);
		List<Tickets> list = sf.getCurrentSession().createQuery(jpql, Tickets.class).setParameter("bus", b).setParameter("date", d).getResultList();
		return list;
	}

}
