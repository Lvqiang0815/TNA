package com.oracle.tna.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.oracle.tna.domain.Admin;
import com.oracle.tna.exception.AdminException;

@Repository
@Scope("singleton")
public class AdminDAO {
	
	@Resource
	HibernateTemplate hbTemplate;
	
	/**
	 * 根据用户名和密码查询admin
	 * @throws AdminException 
	 */
	@SuppressWarnings("unchecked")
	public Admin findAdmin(String aname,String apassword) throws AdminException{
		List<Admin> admins = hbTemplate.find("from Admin where aname=? and apassword=?",new Object[]{aname,apassword});
		if (admins.isEmpty()) {
			throw new AdminException("用户名或密码错误");
		}
		return admins.get(0);
		
	}

}
