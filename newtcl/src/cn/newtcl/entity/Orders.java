package cn.newtcl.entity;

import java.util.List;

public class Orders{
	
	private Integer id = null;
    private Integer table = null;
    private Double total = null;
	private String time = null;
    private Integer makestatus = null;
    private Integer deliverystatus = null;
    private String describable = null;
    private Integer userId = null;
    private Integer managerId = null;
    private List<subDish> subdishs = null;
    private User user;
    private List<Orderline> orderLines;
    
    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getDescribable() {
        return describable;
    }

    public void setDescribable(String describable) {
        this.describable = describable == null ? null : describable.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public List<subDish> getSubdishs() {
		return subdishs;
	}

	public void setSubdishs(List<subDish> subdishs) {
		this.subdishs = subdishs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orderline> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<Orderline> orderLines) {
		this.orderLines = orderLines;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+"___"+table+"___"+total+"___"+managerId+"___"+describable;
	}

}