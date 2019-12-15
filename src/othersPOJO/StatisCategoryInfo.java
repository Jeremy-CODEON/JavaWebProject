package othersPOJO;

import java.io.Serializable;
import java.util.List;

public class StatisCategoryInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String category;
	private int musicNum;
	private int totalSaleNum;
	private double totalSale;
	public StatisCategoryInfo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public StatisCategoryInfo(String category, int musicNum, int totalSaleNum, double totalSale) {
		super();
		this.category = category;
		this.musicNum = musicNum;
		this.totalSaleNum = totalSaleNum;
		this.totalSale = totalSale;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getMusicNum() {
		return musicNum;
	}
	public void setMusicNum(int musicNum) {
		this.musicNum = musicNum;
	}
	public int getTotalSaleNum() {
		return totalSaleNum;
	}
	public void setTotalSaleNum(int totalSaleNum) {
		this.totalSaleNum = totalSaleNum;
	}
	public double getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(double totalSale) {
		this.totalSale = totalSale;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + musicNum;
		long temp;
		temp = Double.doubleToLongBits(totalSale);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + totalSaleNum;
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
		StatisCategoryInfo other = (StatisCategoryInfo) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (musicNum != other.musicNum)
			return false;
		if (Double.doubleToLongBits(totalSale) != Double.doubleToLongBits(other.totalSale))
			return false;
		if (totalSaleNum != other.totalSaleNum)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StatisCategoryInfo [category=" + category + ", musicNum=" + musicNum + ", totalSaleNum=" + totalSaleNum
				+ ", totalSale=" + totalSale + "]";
	}

}
