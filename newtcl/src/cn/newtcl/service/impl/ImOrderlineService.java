package cn.newtcl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.newtcl.dao.OrderlineMapper;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Orderline;
import cn.newtcl.entity.Orders;
import cn.newtcl.entity.subDish;
import cn.newtcl.service.OrderlineServie;

@Service
public class ImOrderlineService implements OrderlineServie{
	
	@Autowired
	@Qualifier("orderlineMapper")
	private OrderlineMapper orderlineMapper;

	@Override
	public NewReturn add(Orders order) {
		NewReturn result = new NewReturn();
		if(order.getSubdishs().size() < 1 || order.getId() == null){
			result.setCode("0");
			result.setMessage("无订单行或无效订单号");
			return result;
		}
		List<subDish> list = order.getSubdishs();
		
		int temp = orderlineMapper.add(list, order);
		if(temp > 0){
			result.setCode("1");
			result.setMessage("订单行添加成功!");
		}else{
			result.setCode("0");
			result.setMessage("数据添加失败");
		}
		return result;
	}

	@Override
	public NewReturn delete(Orders order) {
		NewReturn result = new NewReturn();
		if(order.getId() == null){
			result.setCode("0");
			result.setMessage("无效订单号");
			return result;
		}
		
		int temp = orderlineMapper.delete(order);
		if(temp > 0){
			result.setCode("1");
			result.setMessage("订单所有行数据删除成功!");
		}else{
			result.setCode("0");
			result.setMessage("订单行数据删除失败!");
		}
		return result;
	}

	@Override
	public NewReturn getOrderList(Orders order) {
		NewReturn result = new NewReturn();
		if(order.getId() == null){
			result.setCode("0");
			result.setMessage("无效订单号");
			return result;
		}
		List<subDish> temp = orderlineMapper.getOrderList(order);
		if(temp.size() > 0){
			result.setCode("1");
			result.setMessage("数据查询成功");
			result.setObj(temp);
		}else{
			result.setCode("0");
			result.setMessage("未查询到该订单相关数据!");
		}
		
		return result;
	}

	@Override
	public NewReturn find(Map<String, Object> map) {
		
		NewReturn result = new NewReturn();
		List<subDish> temp = orderlineMapper.find(map);
		if(temp.size() > 0){
			result.setCode("1");
			result.setMessage("查询成功");
			result.setObj(temp);
		}else{
			result.setCode("0");
			result.setMessage("未查询到相关啊数据!");
		}
		
		return result;
	}

	@Override
	public NewReturn edit(Orderline line) {
		NewReturn result = new NewReturn();
		if(line.getId() == null){
			result.setCode("0");
			result.setMessage("无效订单行");
			return result;
		}
		int temp = orderlineMapper.edit(line);
		if(temp > 0){
			result.setCode("1");
			result.setMessage("数据修改成功");
		}else{
			result.setCode("0");
			result.setMessage("数据修改失败");
		}
		
		return result;
	}

	@Override
	public NewReturn changMakeStatus(int id,int status,int staff) {
		NewReturn result = new NewReturn();
		Orderline line = new Orderline();
		line.setId(id);
		line.setMakestatus(status);
		line.setStaffId(staff);
		int temp = orderlineMapper.edit(line);
		if(temp > 0){
			result.setCode("1");
			result.setMessage("状态修改成功");
		}else{
			result.setCode("0");
			result.setMessage("状态修改失败");
		}
		
		return result;
	}
	
	@Override
	public NewReturn changDeliveryStatus(int id,int status,int staff) {
		NewReturn result = new NewReturn();
		Orderline line = new Orderline();
		line.setId(id);
		line.setDeliverystatus(status);
		line.setStaffId(staff);
		int temp = orderlineMapper.edit(line);
		if(temp > 0){
			result.setCode("1");
			result.setMessage("状态修改成功");
		}else{
			result.setCode("0");
			result.setMessage("状态修改失败");
		}
		
		return result;
	}

	@Override
	public int completionDegree(Orderline line) {
		return orderlineMapper.completionDegree(line);
	}

	@Override
	public List<subDish> getStaffAnalysis(Integer id) {
		if(id == null || id == 0){
			return null;
		}
		List<subDish> list = new ArrayList<subDish>();
		list = orderlineMapper.getStaffAnalysis(id);
		
		return list;
	}

	
}
