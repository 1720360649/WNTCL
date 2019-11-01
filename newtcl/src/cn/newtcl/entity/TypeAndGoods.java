package cn.newtcl.entity;

public class TypeAndGoods {

	private Integer typeId = null;
	private String tyupeName = null;
	private Integer goodsId = null;
	private String goodsName = null;
	private String photo = null;
	private Double oldprice = null;
	private Double nowprice = null;
	private Double cost = null;
	private Integer stock = null;
	private String describable = null;
	private Integer status = null;

	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTyupeName() {
		return tyupeName;
	}
	public void setTyupeName(String tyupeName) {
		this.tyupeName = tyupeName;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Double getOldprice() {
		return oldprice;
	}
	public void setOldprice(Double oldprice) {
		this.oldprice = oldprice;
	}
	public Double getNowprice() {
		return nowprice;
	}
	public void setNowprice(Double nowprice) {
		this.nowprice = nowprice;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescribable() {
		return describable;
	}
	public void setDescribable(String describable) {
		this.describable = describable;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	 
}