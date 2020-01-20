package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Routes;

@Repository
public class RoutesDaoImpl implements IRoutesDao {

	@Autowired
	SessionFactory sf;
	
	@Override
	public List<Routes> getAllRoutes() {
		String jpql = "select r from Routes r";
		List<Routes> list = sf.getCurrentSession().createQuery(jpql, Routes.class).getResultList();
		return list;
	}

	@Override
	public Routes getRouteById(int id) {
		String jpql = "select r from Routes r left outer join fetch r.buses where r.id = :id";
		return sf.getCurrentSession().createQuery(jpql, Routes.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public String addRoutes(Routes r) {
		sf.getCurrentSession().persist(r);
		return "Routes added successfully";
	}

	@Override
	public String removeRoutes(int id) {
		Routes r = sf.getCurrentSession().get(Routes.class, id);
		sf.getCurrentSession().delete(r);
		return "Routes removed successfully";
	}

	@Override
	public String updateRoutes(Routes r) {
		sf.getCurrentSession().update(r);
		return "Routes updated successfully";
	}

}
