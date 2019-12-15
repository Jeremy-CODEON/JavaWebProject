package org.fkit.domain;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private double costSum;//�����ܶ�
	private int state;//����״̬��0��δ������֣�1����ȷ�ϣ�2����ȷ�ϣ�3������ɣ�
	
	private User user;//�����һ������һ���û���
	private List<OrderMusic>  orderMusicList;//�����һ�������ܶ�����֣�

	public Order() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}

	public Order(int id, double costSum, int state, User user, List<OrderMusic> orderMusicList) {
		super();
		this.id = id;
		this.costSum = costSum;
		this.state = state;
		this.user = user;
		this.orderMusicList = orderMusicList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCostSum() {
		return costSum;
	}

	public void setCostSum(double costSum) {
		this.costSum = costSum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderMusic> getOrderMusicList() {
		return orderMusicList;
	}

	public void setOrderMusicList(List<OrderMusic> orderMusicList) {
		this.orderMusicList = orderMusicList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(costSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((orderMusicList == null) ? 0 : orderMusicList.hashCode());
		result = prime * result + state;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (Double.doubleToLongBits(costSum) != Double.doubleToLongBits(other.costSum))
			return false;
		if (id != other.id)
			return false;
		if (orderMusicList == null) {
			if (other.orderMusicList != null)
				return false;
		} else if (!orderMusicList.equals(other.orderMusicList))
			return false;
		if (state != other.state)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", costSum=" + costSum + ", state=" + state + ", user=" + user + ", orderMusicList="
				+ orderMusicList + "]";
	}

	
}
