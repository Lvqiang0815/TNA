package com.oracle.tna.dao;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.oracle.tna.domain.UserScore;

public class UserScoreDAO {
	
	@Resource
	HibernateTemplate hbTemplate;
	
	/**
	 * 插入UserScore对象
	 */
	public void insertUserScore(UserScore userScore){
		hbTemplate.save(userScore);
	}

}
