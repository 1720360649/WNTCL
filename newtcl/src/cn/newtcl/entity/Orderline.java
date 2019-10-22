package cn.newtcl.entity;

public class Orderline {
	
    private Integer id;
    private Integer goodsId = null;
    private Integer orderId = null;
    private Integer count = null;
    private Integer makestatus = null;
    private Integer deliverystatus = null;
    private Double price = null;
    private Integer staffId =  null;
    private Goods goods;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
}