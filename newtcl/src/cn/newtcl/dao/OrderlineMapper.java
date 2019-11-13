package cn.newtcl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.newtcl.entity.Orderline;
import cn.newtcl.entity.Orders;
import cn.newtcl.entity.subDish;

public interface OrderlineMapper {

	public int add(@Param("list") List<subDish> list,@Param("order") Orders order);
	
	public int delete(Orders order);
	
	public List<subDish> getOrderList(Orders order);
	
	public List<subDish> find(Map<String, Object> map);
	
	public int edit(Orderline line);
	
	public int completionDegree(Orderline line);
	
	public List<subDish> getStaffAnalysis(Integer id);

	public List<subDish> getStaffAnalysisOne(@Param("id") Integer id,@Param("staffid") Integer staffid);

	public List<subDish> getorderlineAnalysisForGoods(Integer id);
	
	public List<subDish> getorderlineAnalysisForType(Integer id);
	
}