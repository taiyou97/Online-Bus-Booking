package com.app.dao;

import java.util.List;

import com.app.pojos.Routes;

public interface IRoutesDao {
	public List<Routes> getAllRoutes();
	public Routes getRouteById(int id);
	public String addRoutes(Routes r);
	public String removeRoutes(int id);
	public String updateRoutes(Routes r);
}
