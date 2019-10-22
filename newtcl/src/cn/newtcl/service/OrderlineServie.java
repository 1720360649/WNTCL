package cn.newtcl.service;

import java.util.Map;

import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Orderline;
import cn.newtcl.entity.Orders;

public interface OrderlineServie {

	/**
	 * 添加
	 */
	public NewReturn add(Orders order);
	
	/**
	 * 删除(根据订单id)
	 */
	public NewReturn delete(Orders order);
	
	/**
	 * 获取整个订单的菜(根据订单id)
	 */
	public NewReturn getOrderList(Orders order);
	
	//任意条件查询
	public NewReturn find(Map<String, Object> map);
	
	//修改
	public NewReturn edit(Orderline line);
	
	//状态修改
	public NewReturn changMakeStatus(int id,int status,int staff);
	
	//状态修改
	public NewReturn changDeliveryStatus(int id,int status,int staff);
	
	//订单完成度查询
	public int completionDegree(Orderline line);
	
}
