package com.jabasoft.mms.customermanagement.test.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class UserDao {

	private UserRepo repo;

	@Autowired
	public UserDao(UserRepo repo) {

		this.repo = repo;
	}

	public User save(User user){
		return repo.save(user);
	}

	public User find(Long id){
		return repo.findById(id).orElse(null);
	}
}
