package cn.newtcl.entity;

public class subDish extends Goods{
	
	private Integer number = null;
	private String flavor = null;
	private Double price = null;
	private Integer table = null;
	private Integer orderid = null;
	private String time = null;
	private Integer typeId = null;
	private Integer goodsId = null;
	private Integer userId = null;
	private Integer staffId = null;
	private Integer makestatus = null;
	private Integer deliverystatus = null;
	
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

	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getMakestatus() {
		return makestatus;
	}

	public void setMakestatus(Integer makestatus) {
		this.makestatus = makestatus;
	}

	public Integer getDeliverystatus() {
		return deliverystatus;
	}

	public void setDeliverystatus(Integer deliverystatus) {
		this.deliverystatus = deliverystatus;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
}
