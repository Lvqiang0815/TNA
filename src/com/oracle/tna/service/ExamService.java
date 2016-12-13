package com.oracle.tna.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.oracle.tna.dao.ItemDAO;
import com.oracle.tna.dao.ScoreDAO;
import com.oracle.tna.domain.Item;
import com.oracle.tna.domain.Score;
import com.oracle.tna.domain.User;

@Service("examService")
@Scope("singleton")
public class ExamService {
	
	@Resource
	ItemDAO itemDAO;
	
	@Resource
	ScoreDAO scoreDAO;
	
	/**
	 * 查询所有题目
	 * return List<Item>
	 */
	public List<Item> queryAllItem(){
		return itemDAO.retriveItem();
	}
	
	/**
	 * 判分
	 * return Score
	 */
	public Score countScore(List<Item> itms,List<String> uanswers,User user){
		Score score = null;// 封装Score对象
		List<Character> sanswers = new ArrayList<Character>();// 存储itms的answer
		
		double total_score=100;
		double user_score = 0;
		double each_score = total_score / itms.size();
		for (int i = 0 ;i < itms.size();i++) {
			if (itms.get(i).getAnswer() == uanswers.get(i).charAt(0)) {
				user_score += each_score;
			}
			sanswers.add(itms.get(i).getAnswer());
			//score = new Score(1, new Date(), score_num , uanswers.toString(),sanswers.toString());
		}
		int s_score = (int)user_score;
		score = new Score(new Date(), s_score , uanswers.toString(),sanswers.toString(),user);
		scoreDAO.insert(score);
		return score;
	}
	
	/**
	 * 查询所有Score对象
	 */
	public List<Score> queryAllScore(){
		return scoreDAO.queryAllScore();
	}
}
