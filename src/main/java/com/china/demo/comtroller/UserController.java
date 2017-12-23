package com.china.demo.comtroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.china.demo.data.Result;
import com.china.demo.domain.User;
import com.china.demo.service.UserService;

@RestController
@RequestMapping("/mapper")
public class UserController {
	@Autowired
	private UserService procTestService;

	@RequestMapping(value = "add/{id}/{username}/{password}", method = RequestMethod.GET)
	public Result<Integer> add(@PathVariable("id") String id, @PathVariable("username") String username,
			@PathVariable("password") String password) {
		User procTest = new User(id,username,password);
		return new Result<Integer>(procTestService.create(procTest));
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "findall", method = RequestMethod.GET)
	public Result<List> findall() {
		List<User> findAll = procTestService.findAll();
		return new Result<List>(findAll);
	}

}
