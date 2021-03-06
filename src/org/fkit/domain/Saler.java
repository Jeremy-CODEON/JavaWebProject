package org.fkit.domain;

import java.io.Serializable;
import java.util.List;

public class Saler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String password;//密码
	private String sale_category;//负责销售的类别
	
	public Saler() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Saler(int id, String password, String sale_category) {
		super();
		this.id = id;
		this.password = password;
		this.sale_category = sale_category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSale_category() {
		return sale_category;
	}
	public void setSale_category(String sale_category) {
		this.sale_category = sale_category;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((sale_category == null) ? 0 : sale_category.hashCode());
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
		Saler other = (Saler) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (sale_category == null) {
			if (other.sale_category != null)
				return false;
		} else if (!sale_category.equals(other.sale_category))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Saler [id=" + id + ", password=" + password + ", sale_category=" + sale_category + "]";
	}
}
