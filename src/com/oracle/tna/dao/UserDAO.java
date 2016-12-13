package com.oracle.tna.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.oracle.tna.domain.User;
import com.oracle.tna.exception.UserException;

@Repository("userDAO")
@Scope("singleton")
public class UserDAO {
	
	@Resource
	HibernateTemplate hbTemplate;
	
	/**
	 * 插入用户信息
	 */
	public void insert(User user){
		hbTemplate.save(user);
	}

	/**
	 *查询所有用户
	 */
	@SuppressWarnings("unchecked")
	public List<User> queryAllUsers(){
		return hbTemplate.find("from User");
		
	}
	
	/**
	 * 根据username和password查询用户信息
	 * @throws UserException 
	 */
	@SuppressWarnings("unchecked")
	public User findByUs(String username,String password) throws UserException{
		List<User> users = hbTemplate.find("from User where username=? and password=?",new Object[]{username,password});
		if (users.isEmpty()) {
			throw new UserException("该用户不存在");
		}
		return users.get(0);
	}
	
	/**
	 * 根据id修改用户信息
	 */
	public void update(User user){
		hbTemplate.update("User", user);
	}
}
