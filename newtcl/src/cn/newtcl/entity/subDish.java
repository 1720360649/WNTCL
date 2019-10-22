package cn.newtcl.entity;

public class subDish extends Goods{
	
	private Integer number = null ;
	private String flavor = null;
	private Double price = null;
	private Integer table = null;
	private Integer orderid = null;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getTable() {
		return table;
	}

	public void setTable(Integer table) {
		this.table = table;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	
	
}
