package cn.newtcl.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.newtcl.dao.OrderlineMapper;
import cn.newtcl.dao.OrdersMapper;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Orders;
import cn.newtcl.service.OrdersService;

@Service
public class ImOrderService implements OrdersService{
	
	@Resource
	private OrdersMapper ordersMapper;
	
	@Autowired
	@Qualifier("orderlineMapper")
	private OrderlineMapper orderlineMapper;

	@Override
	public NewReturn add(Orders order) {
		NewReturn result = new NewReturn();

		if(order == null){
			result.setCode("0");
			result.setMessage("用户对象错误或不存在!");
			return result;
		}
		int adds = ordersMapper.add(order);
		if (adds > 0) {
			result.setCode("1");
			result.setMessage("数据添加成功!");
			int id = order.getId();
			result.setObj(id);
		}else{
			result.setCode("0");
			result.setMessage("数据添加失败!");
		}
		return result;
	}

	@Override
	public NewReturn delete(Orders order) {
		NewReturn result = new NewReturn();
		if(order.getId() == null || order instanceof Orders){
			result.setCode("0");
			result.setMessage("用户对象错误或不存在!");
			return result;
		}
		int adds = ordersMapper.delete(order);
		if (adds > 0) {
			result.setCode("1");
			result.setMessage("数据删除成功!");
		}else{
			result.setCode("0");
			result.setMessage("数据删除失败!");
		}
		return result;
	}

	@Override
	public NewReturn edit(Orders order) {
		NewReturn result = new NewReturn();
		if(order.getId() == null || !(order instanceof Orders)){
			result.setCode("0");
			result.setMessage("用户对象错误或不存在!");
			return result;
		}
		int adds = ordersMapper.edit(order);
		if (adds > 0) {
			result.setCode("1");
			result.setMessage("数据修改成功!");
		}else{
			result.setCode("0");
			result.setMessage("数据修改失败!");
		}
		return result;
	}

	@Override
	public NewReturn getMe(Orders order) {
		NewReturn result = new NewReturn();
		if(order.getId() == null || order instanceof Orders){
			result.setCode("0");
			result.setMessage("用户对象错误或不存在!");
			return result;
		}
		Orders me = ordersMapper.getMe(order);
		if(me.getId() == null){
			result.setCode("0");
			result.setMessage("获取订单详情失败");
		}else{
			result.setCode("1");
			result.setMessage("获取订单详情成功");
			result.setObj(me);
		}
		
		return result;
	}

	@Override
	public NewReturn find(Orders order) {
		NewReturn result = new NewReturn();
		List<Orders> orders = ordersMapper.find(order);
		if(orders == null || orders.size() <= 0){
			result.setCode("0");
			result.setMessage("查找失败,未查询到相应数据");
		}else{
			result.setCode("1");
			result.setMessage("查询成功");
			result.setObj(orders);
		}
		return result;
	}

	@Override
	public NewReturn findForMap(Map<String, Object> map) {
		NewReturn result = new NewReturn();
		List<Map<String, Object>> orders = ordersMapper.findForMap(map);
		if(orders == null){
			result.setCode("0");
			result.setMessage("查找失败,未查询到相应数据");
		}else{
			result.setCode("1");
			result.setMessage("查询成功");
			result.setObj(orders);
		}
		return result;
	}

	@Override
	public List<Orders> BusinessAnalysis(Integer id) {
		
		return ordersMapper.BusinessAnalysis(id);
	}

	
}
