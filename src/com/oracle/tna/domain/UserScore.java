package com.oracle.tna.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserScore implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int usid;
	@OneToOne
	private User user;
	@OneToOne
	private Score score;

	public UserScore() {
		super();
	}

	public UserScore(int usid,Score score) {
		super();
		this.usid = usid;
		this.score = score;
	}
	
	public UserScore(User user, Score score) {
		super();
		this.user = user;
		this.score = score;
	}
	
	public UserScore(int usid, User user, Score score) {
		super();
		this.usid = usid;
		this.user = user;
		this.score = score;
	}

	public int getUsid() {
		return usid;
	}

	public void setUsid(int usid) {
		this.usid = usid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "UserScore [usid=" + usid + ", user=" + user + ", score="
				+ score + "]";
	}

}
