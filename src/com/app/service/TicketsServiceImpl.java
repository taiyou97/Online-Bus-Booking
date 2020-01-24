package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ITicketsDao;
import com.app.pojos.Tickets;

@Service
@Transactional
public class TicketsServiceImpl implements ITicketsService {

	@Autowired
	private ITicketsDao dao;
	
	@Override
	public Tickets bookTickets(Tickets t, int busId, int customerId) {
		return dao.bookTickets(t, busId, customerId);
	}

	@Override
	public Tickets cancelTickets(int ticketId) {
		return dao.cancelTickets(ticketId);
	}

	@Override
	public List<Tickets> getTicketsofUser(int userId) {
		return dao.getTicketsofUser(userId);
	}

	@Override
	public List<Tickets> getTicketsofBus(int busId, String date) {
		return dao.getTicketsofBus(busId, date);
	}

}
