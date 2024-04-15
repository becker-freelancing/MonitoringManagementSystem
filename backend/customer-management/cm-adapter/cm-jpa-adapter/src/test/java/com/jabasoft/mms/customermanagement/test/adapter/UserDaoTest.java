package com.jabasoft.mms.customermanagement.test.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.customermanagement.MmsDaoImplTest;
import com.jabasoft.mms.customermanagement.domain.model.Country;

@MmsDaoImplTest
class UserDaoTest {

	UserDao dao;

	@Autowired
	public UserDaoTest(UserDao dao) {

		this.dao = dao;
	}

	@Test
	void test(){

		Set<User> saved = new HashSet<>();

		for(int i = 0; i < 1500; i++){
			User save = dao.save(createUser());
			saved.add(save);
		}

		for (User user : saved) {
			User find = dao.find(user.getId());

			assertEquals(user, find);
		}

	}

	private User createUser() {

		User user = new User();
		user.setId(new Random().nextLong(0, 10));
		user.setName(UUID.randomUUID().toString());

		Address address = new Address();
		address.setStreet(UUID.randomUUID().toString());
		address.setCountry(Math.random() > 0.7 ? Country.GERMANY : Math.random() < 0.4 ? Country.SPAIN : Country.USA);

		user.setAddress(address);
		address.setUser(user);

		return user;
	}

}