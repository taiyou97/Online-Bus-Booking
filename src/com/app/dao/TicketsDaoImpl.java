package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Bus;
import com.app.pojos.Tickets;
import com.app.pojos.User;

@Repository
public class TicketsDaoImpl implements ITicketsDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public Tickets bookTickets(Tickets t, int busId, int customerId) {
		Bus b = sf.getCurrentSession().get(Bus.class, busId);
		User u = sf.getCurrentSession().get(User.class, customerId);
		b.addTickets(t);
		u.addTickets(t);
		sf.getCurrentSession().merge(b);
		sf.getCurrentSession().merge(u);
		return t;
	}

	@Override
	public Tickets cancelTickets(int ticketId) {
		Tickets t = sf.getCurrentSession().get(Tickets.class, ticketId);
		Bus b = sf.getCurrentSession().get(Bus.class, t.getBusId().getId());
		b.removeTickets(t);
		t.setNoOfSeats((byte)0);
		sf.getCurrentSession().merge(b);
		sf.getCurrentSession().merge(t);
		return null;
	}

	@Override
	public List<Tickets> getTicketsofUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tickets> getTicketsofBus(int busId, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
