package com.oracle.tna.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.tna.dao.AdminDAO;
import com.oracle.tna.domain.Admin;
import com.oracle.tna.exception.AdminException;

@Service("adminService")
@Scope("singleton")
public class AdminService {
	
	@Resource
	AdminDAO adminDAO;
	
	/**
	 * 根据用户名和密码查询admin
	 */
	public Admin findAdmin(String aname,String apassword) throws AdminException {
		return adminDAO.findAdmin(aname,apassword);
	}

}
