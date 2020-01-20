package com.app.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Bus;
import com.app.pojos.DayFromDate;
import com.app.pojos.Routes;
import com.app.pojos.Seats;

@Repository
public class BusDaoImpl implements IBusDao{

	@Autowired
	SessionFactory sf;
	
	@Override
	public List<Bus> getAllBus() {
		String jpql = "select b from Bus b";
		return sf.getCurrentSession().createQuery(jpql, Bus.class).getResultList();
	}

	@Override
	public Bus getBusById(int id) {
		String jpql = "select b from Bus b where b.id = :id";
		return sf.getCurrentSession().createQuery(jpql, Bus.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public String addBus(Bus b, int routeId) {
		Routes r = sf.getCurrentSession().get(Routes.class, routeId);
		r.addBuses(b);
		b.addFrequency(b.getFrequency());
		sf.getCurrentSession().merge(r);
		return "Bus added Successfully";
	}

	@Override
	public String removeBus(int id) {
		Bus b = sf.getCurrentSession().get(Bus.class, id);
		Routes r = sf.getCurrentSession().get(Routes.class, b.getRouteId().getId());
		r.removeBuses(b);
		sf.getCurrentSession().merge(r);
		return "Bus removed Successfully";
	}

	@Override
	public String updateBus(Bus b) {
		Bus oldBus = sf.getCurrentSession().get(Bus.class, b.getId());
		oldBus.setCapacity(b.getCapacity());
		oldBus.setType(b.getType());
		oldBus.setArrival(b.getArrival());
		oldBus.setDestination(b.getDestination());
		oldBus.setPrice(b.getPrice());
		oldBus.setFrequency(b.getFrequency());
		sf.getCurrentSession().update(oldBus);
		return "Bus updated Successfully";
	}

	@Override
	public List<Bus> getBusByRoutes(String source, String destination, String date) {
		int day = DayFromDate.getDayInt(date);
		List<Bus> buses = new ArrayList<Bus>();
		String jpql1 = "select r from Routes r where r.source = :source and r.destination = :destination";
		Routes r = sf.getCurrentSession().createQuery(jpql1, Routes.class).setParameter("source", source).setParameter("destination", destination).getSingleResult();
		System.out.println(r);
		String jpql2 = "select b from Bus b where b.routeId = :routeId";
		List<Bus> list = sf.getCurrentSession().createQuery(jpql2, Bus.class).setParameter("routeId", r).getResultList();
		//String jpql3 = "select s from Seats s where s.busId = :busId and s.bookDate = :bookDate";
		//Seats s;
		for (Bus b : list) {
			b.toString();
			if(b.getFrequency().isRuns(day))
			{
				/*
				 try {
					s = sf.getCurrentSession().createQuery(jpql3, Seats.class).setParameter("busId", b).setParameter("bookDate", DayFromDate.getLocalDate(date)).getSingleResult();
				}catch (RuntimeException e) {
					e.printStackTrace();
					s = new Seats(LocalDate.parse(date), b.getCapacity());
					b.addSeats(s);
					sf.getCurrentSession().merge(b);
				}
				b.addSeats(s);
				 */
				buses.add(b);
			}
			}
		return buses;
	}

}
