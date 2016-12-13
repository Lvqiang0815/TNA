package com.oracle.tna.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.tna.dao.UserDAO;
import com.oracle.tna.domain.User;
import com.oracle.tna.exception.UserException;

@Service("userService")
@Scope("singleton")
public class UserService {
	@Resource
	UserDAO userDAO;
	
	/**
	 * 用户注册
	 */
	public void register(User user) throws UserException {
		userDAO.insert(user);
	}
	
	/**
	 * 查询所有用户信息
	 */
	public List<User> findAllUs(){
		List<User> allus = userDAO.queryAllUsers();
		return allus;
		
	}
	
	/**
	 * 根据username和password查询用户信息
	 */
	public User findByUs(String username,String password) throws UserException{
		return userDAO.findByUs(username, password);
	}
	
	/**
	 * 根据id修改用户信息
	 */
	public void update(User user) throws UserException {
		userDAO.update(user);
	}

}
