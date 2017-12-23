package com.china.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.china.demo.domain.User;
import com.china.demo.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper procTestMapper;

	public int create(User pt) {
		return procTestMapper.create(pt);
	}

	public List<User> findAll() {
		return procTestMapper.findAll();
	}
}
