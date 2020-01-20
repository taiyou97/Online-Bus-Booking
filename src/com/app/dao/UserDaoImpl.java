package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.User;

@Repository
public class UserDaoImpl implements IUserDao {
	
	@Autowired
	private SessionFactory sf;
	
	@Override
	public User validateUser(String email, String pass) {
		System.out.println("validateUser()");
		String jpql = "select u from User u where u.email = :email and u.password = :pass ";
		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("email", email).setParameter("pass", pass).getSingleResult();
	}

	@Override
	public void registerUser(User u) {
		System.out.println("registerUser()");
		sf.getCurrentSession().persist(u);
	}

	@Override
	public void updatePassword(String email, String newPass) {
		System.out.println("updatePassword");
		String jpql = "select u from User u where u.email = :email";
		User u = sf.getCurrentSession().createQuery(jpql, User.class).setParameter("email", email).getSingleResult();
		u.setPassword(newPass);
		sf.getCurrentSession().update(u);
	}

	@Override
	public void updateUser(User u) {
		System.out.println("updateUser()");
		sf.getCurrentSession().update(u);
	}

	@Override
	public void updatePhone(String email, Long newPhone) {
		System.out.println("updatePhone()");
		String jpql = "select u from User u where u.email = :email";
		User oldUser = sf.getCurrentSession().createQuery(jpql, User.class).setParameter("email", email).getSingleResult();
		oldUser.setPhone(newPhone);
		sf.getCurrentSession().update(oldUser);
	}

}
