package cn.newtcl.service;

import java.util.List;
import java.util.Map;

import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Orders;

public interface OrdersService {

	/**
	 * 添加
	 * @param order
	 * @return NewReturn
	 */
	public NewReturn add(Orders order);
	
	/**
	 * 删除
	 * @param order
	 * @return int
	 */
	public NewReturn delete(Orders order);
	
	/**
	 * 修改
	 * @param order
	 * @return int
	 */
	public NewReturn edit(Orders order);
	
	/**
	 * 根据ID获取详情
	 * @param order
	 * @return
	 */
	public NewReturn getMe(Orders order);

	/**
	 *根据map条件查找
	 * @param map
	 * @return List<Orders>
	 */
	public NewReturn find(Orders order);
	
	/**
	 * 将返回列表改变为map类型数据
	 * @param map
	 * @return List<Map<String, Object>>
	 */
	public NewReturn findForMap(Map<String, Object> map);
	
	/**
	 * 
	 * @return Orders 只含 total , time
	 */
	public List<Orders> BusinessAnalysis(Integer id);
	
}
