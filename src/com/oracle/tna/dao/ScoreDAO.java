package com.oracle.tna.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.oracle.tna.domain.Score;

@Repository("scoreDAO")
@Scope("singleton")
public class ScoreDAO {
	
	@Resource
	HibernateTemplate hbTemplate;
	
	/**
	 * 插入分数
	 */
	public void insert(Score score){
		hbTemplate.save(score);
	}
	
	/**
	 * 查询所有分数信息
	 */
	@SuppressWarnings("unchecked")
	public List<Score> queryAllScore(){
		return (List<Score>)hbTemplate.find("from Score");
	}

}
