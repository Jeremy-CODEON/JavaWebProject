package org.fkit.domain;

import java.io.Serializable;
import java.util.List;

public class OrderMusic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Order order;//订单（外键）
	private Music music;//音乐（外键）
	
	public OrderMusic() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public OrderMusic(int id, Order order, Music music) {
		super();
		this.id = id;
		this.order = order;
		this.music = music;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((music == null) ? 0 : music.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		OrderMusic other = (OrderMusic) obj;
		if (id != other.id)
			return false;
		if (music == null) {
			if (other.music != null)
				return false;
		} else if (!music.equals(other.music))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderMusic [id=" + id + ", order=" + order + ", music=" + music + "]";
	}
	
}
