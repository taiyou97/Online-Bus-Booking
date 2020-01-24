package com.app.service;

import com.app.pojos.User;

public interface IUserService {
	public User validateUser(String email, String pass);
	public User getUser(String email);
	public  void registerUser(User u);
	public void updatePassword(String email, String newPass);
	public void updateUser(User u);
	public void updatePhone(String email, Long newPhone);
}
