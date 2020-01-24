package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUserDao;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao dao;
	
	@Override
	public User validateUser(String email, String pass) {
		return dao.validateUser(email, pass);
	}

	@Override
	public void registerUser(User u) {
		dao.registerUser(u);
	}

	@Override
	public void updatePassword(String email, String newPass) {
		dao.updatePassword(email, newPass);
	}

	@Override
	public void updateUser(User u) {
		dao.updateUser(u);
	}

	@Override
	public void updatePhone(String email, Long newPhone) {
		dao.updatePhone(email, newPhone);
	}

	@Override
	public User getUser(String email) {
		return dao.getUser(email);
	}

}
