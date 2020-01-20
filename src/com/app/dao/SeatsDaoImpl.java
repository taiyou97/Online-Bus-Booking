package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Seats;

@Repository
public class SeatsDaoImpl implements ISeatsDao{

	@Autowired
	SessionFactory sf;
	
	@Override
	public Seats getSeatsByBusId(int busId, String date) {
		return null;
	}

	@Override
	public String bookSeatsByBus(int busId, String date, int noOfSeats) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addSeatsByBus(int busId, String date, int noOfSeats) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeSeatsByBus(int busId, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
