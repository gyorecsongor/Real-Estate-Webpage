package com.ingatlan2.ingatlan.service;

import com.ingatlan2.ingatlan.entities.User;

public interface UserService {
	public String registerUser(User user);

	public User findByEmail(String email);

	public String userActivation(String code);
}
