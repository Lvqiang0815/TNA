package com.oracle.tna.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.tna.dao.ScoreDAO;
import com.oracle.tna.dao.UserDAO;
import com.oracle.tna.domain.Score;
import com.oracle.tna.domain.User;
import com.oracle.tna.domain.UserScore;

@Service("scoreService")
@Scope("singleton")
public class ScoreService {
	
	@Resource
	ScoreDAO scoreDAO;
	
	@Resource
	UserDAO userDAO;
	
	/**
	 * 查询所有的Score对象
	 */
	public List<Score> queryAllScores(){
		return scoreDAO.queryAllScore();
	}
	
	/**
	 * 查询所有的UserScore对象
	 */
	public List<UserScore> getAllUserScores(){
		List<Score> allscores = queryAllScores();
		List<User> allusers = userDAO.queryAllUsers();
		
		List<UserScore> userScores = new ArrayList<UserScore>();
		for (int i = 0; i < allscores.size(); i++) {
			//userScores.add(new UserScore(1, allusers.get(i), allscores.get(i)));
			userScores.add(new UserScore(1, allscores.get(i)));
		}
		System.out.println("1111111111111");
		System.out.println(userScores);
		System.out.println(userScores.size());
		return userScores;
		
	}

}
