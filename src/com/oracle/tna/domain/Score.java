package com.oracle.tna.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Score implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int uid;
	private Date date;
	private int score;
	private String answer;
	private String s_answer;
	@OneToOne
	private User user;

	public Score() {
		super();
	}

	public Score(Date date, int score, String answer, String s_answer) {
		super();
		this.date = date;
		this.score = score;
		this.answer = answer;
		this.s_answer = s_answer;
	}
	public Score(int uid, Date date, int score, String answer, String s_answer) {
		super();
		this.uid = uid;
		this.date = date;
		this.score = score;
		this.answer = answer;
		this.s_answer = s_answer;
	}
	
	public Score(int uid, Date date, int score, String answer, String s_answer, User user) {
		super();
		this.uid = uid;
		this.date = date;
		this.score = score;
		this.answer = answer;
		this.s_answer = s_answer;
		this.user = user;
	}
	public Score(Date date, int score, String answer, String s_answer, User user) {
		super();
		this.date = date;
		this.score = score;
		this.answer = answer;
		this.s_answer = s_answer;
		this.user = user;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getS_answer() {
		return s_answer;
	}

	public void setS_answer(String s_answer) {
		this.s_answer = s_answer;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Score [uid=" + uid + ", date=" + date + ", score=" + score
				+ ", answer=" + answer + ", s_answer=" + s_answer + "]";
	}

}
