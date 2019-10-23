package cn.newtcl.dao;

import java.util.List;
import java.util.Map;

import cn.newtcl.entity.Orders;

public interface OrdersMapper {
	
	public int add(Orders order);
	
	public int delete(Orders order);
	
	public int edit(Orders order);
	
	public Orders getMe(Orders order);

	public List<Orders> find(Orders order);
	
	public List<Map<String, Object>> findForMap(Map<String, Object> map);
	
	public List<Orders> BusinessAnalysis();
}