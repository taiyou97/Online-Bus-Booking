package com.app.utility;

import com.app.pojos.Tickets;

public class TicketUtils {
	public Tickets t;
	public Integer userId;
	public Integer busId;

	public TicketUtils(Tickets t, Integer userId, Integer busId) {
		super();
		System.out.println("TicketUtils");
		this.t = t;
		this.userId = userId;
		this.busId = busId;
	}

}
