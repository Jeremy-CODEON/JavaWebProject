package org.fkit.domain;

import java.io.Serializable;

public class Behavior implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private User user;//（外键）
	private Music music;//（外键）
	private int behavior;//用户行为，1浏览2购买
	private int year;
	private int month;
	private int day;
	public Behavior() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Behavior(int id, User user, Music music, int behavior, int year, int month, int day) {
		super();
		this.id = id;
		this.user = user;
		this.music = music;
		this.behavior = behavior;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	public int getBehavior() {
		return behavior;
	}
	public void setBehavior(int behavior) {
		this.behavior = behavior;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + behavior;
		result = prime * result + day;
		result = prime * result + id;
		result = prime * result + month;
		result = prime * result + ((music == null) ? 0 : music.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Behavior other = (Behavior) obj;
		if (behavior != other.behavior)
			return false;
		if (day != other.day)
			return false;
		if (id != other.id)
			return false;
		if (month != other.month)
			return false;
		if (music == null) {
			if (other.music != null)
				return false;
		} else if (!music.equals(other.music))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Behavior [id=" + id + ", user=" + user + ", music=" + music + ", behavior=" + behavior + ", year="
				+ year + ", month=" + month + ", day=" + day + "]";
	}

}
