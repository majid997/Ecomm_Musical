package com.app.service;

import java.util.List;

import com.app.pojos.User;

public interface IUserService {
	List<User> getAllUsers();

	User addUser(User transientUser);
}
