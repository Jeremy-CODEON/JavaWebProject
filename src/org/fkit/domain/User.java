package org.fkit.domain;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String loginname;
	private String password;
	private String username;
	private String phone;
	private String mail;
	private Integer age;
	private String avatar;//用户头像url
	private Double browsetime;//平均浏览时间
	private Integer logintime;//登录次数（在注销时统计）
	private Integer classification;//用户画像分类
	private Integer mlmusic_id;//最喜爱的音乐（加权统计）
	
	
	private List<Cart> cartList;//一个用户多个购物车
	private List<Order> orderList;//一个用户多个订单
	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public User(Integer id, String loginname, String password, String username, String phone, String mail, Integer age,
			String avatar, Double browsetime, Integer logintime, Integer classification, Integer mlmusic_id,
			List<Cart> cartList, List<Order> orderList) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.mail = mail;
		this.age = age;
		this.avatar = avatar;
		this.browsetime = browsetime;
		this.logintime = logintime;
		this.classification = classification;
		this.mlmusic_id = mlmusic_id;
		this.cartList = cartList;
		this.orderList = orderList;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<Cart> getCartList() {
		return cartList;
	}
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	public Double getBrowsetime() {
		return browsetime;
	}
	public void setBrowsetime(Double browsetime) {
		this.browsetime = browsetime;
	}
	public Integer getLogintime() {
		return logintime;
	}
	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
	}
	public Integer getClassification() {
		return classification;
	}
	public void setClassification(Integer classification) {
		this.classification = classification;
	}
	public Integer getMlmusic_id() {
		return mlmusic_id;
	}
	public void setMlmusic_id(Integer mlmusic_id) {
		this.mlmusic_id = mlmusic_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((browsetime == null) ? 0 : browsetime.hashCode());
		result = prime * result + ((cartList == null) ? 0 : cartList.hashCode());
		result = prime * result + ((classification == null) ? 0 : classification.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loginname == null) ? 0 : loginname.hashCode());
		result = prime * result + ((logintime == null) ? 0 : logintime.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((mlmusic_id == null) ? 0 : mlmusic_id.hashCode());
		result = prime * result + ((orderList == null) ? 0 : orderList.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (browsetime == null) {
			if (other.browsetime != null)
				return false;
		} else if (!browsetime.equals(other.browsetime))
			return false;
		if (cartList == null) {
			if (other.cartList != null)
				return false;
		} else if (!cartList.equals(other.cartList))
			return false;
		if (classification == null) {
			if (other.classification != null)
				return false;
		} else if (!classification.equals(other.classification))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginname == null) {
			if (other.loginname != null)
				return false;
		} else if (!loginname.equals(other.loginname))
			return false;
		if (logintime == null) {
			if (other.logintime != null)
				return false;
		} else if (!logintime.equals(other.logintime))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (mlmusic_id == null) {
			if (other.mlmusic_id != null)
				return false;
		} else if (!mlmusic_id.equals(other.mlmusic_id))
			return false;
		if (orderList == null) {
			if (other.orderList != null)
				return false;
		} else if (!orderList.equals(other.orderList))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginname=" + loginname + ", password=" + password + ", username=" + username
				+ ", phone=" + phone + ", mail=" + mail + ", age=" + age + ", avatar=" + avatar + ", browsetime="
				+ browsetime + ", logintime=" + logintime + ", classification=" + classification + ", mlmusic_id="
				+ mlmusic_id + ", cartList=" + cartList + ", orderList=" + orderList + "]";
	}
	
	
}
