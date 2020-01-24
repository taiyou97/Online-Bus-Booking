package com.app.service;

import java.util.List;

import com.app.pojos.Tickets;

public interface ITicketsService {
	public Tickets bookTickets(Tickets t, int busId, int customerId);
	public Tickets cancelTickets(int ticketId);
	public List<Tickets> getTicketsofUser(int userId);
	public List<Tickets> getTicketsofBus(int busId, String date);
}
