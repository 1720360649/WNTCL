package cn.newtcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtcl.entity.Information;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Orderline;
import cn.newtcl.entity.Orders;
import cn.newtcl.entity.subDish;
import cn.newtcl.service.impl.ImOrderService;
import cn.newtcl.service.impl.ImOrderlineService;

@Controller
@RequestMapping("waiter")
public class WaiterController {

	//注入Service
	@Resource
	private ImOrderlineService imOrderlineService;
	
	@Resource
	private ImOrderService imOrderService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/getlist")
	public @ResponseBody Map<Integer,List<subDish>> getlist(){
		
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
		
		Map<Integer,List<subDish>> list = subDishList.getOverlist();
		
		if(list == null || list.size() <= 0){
			list =new HashMap<Integer,List<subDish>>();
		}
		
		return  list;
	}
	
	@RequestMapping("/remove")
	public @ResponseBody Information remove(Orders order){
		Integer li = order.getTable();
		Information in = new Information();
		
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
		
		Map<Integer,List<subDish>> list = subDishList.getOverlist();
		if(list == null || list.size() <= 0){
			list =new HashMap<Integer,List<subDish>>();
		}

		if(li == null || li == 0){
			in.setCode("0");
			in.setMessage("未选择删除对象!请重试或联系管理员!");
			return in;
		}
		
	//更改订单状态
		//查询该桌号是否有未完成制作订单
		int orderid = SubDishList.getOverlist().get(li).get(0).getOrderid();
		Orderline line = new Orderline();
		line.setOrderId(orderid);
		int num = imOrderlineService.completionDegree(line);
		//判断是否完成订单内所有菜
		if(num<=0){
			Orders temporder = new Orders();
			temporder.setId(orderid);
			temporder.setMakestatus(1);
			temporder.setDeliverystatus(1);
			NewReturn re = imOrderService.edit(temporder);
			if(re.getCode().equals("0")){
				in.setCode("0");
				in.setMessage("订单状态更改失败,请重试或联系管理员");
			//	in.setMessage(re.getMessage());
				return in;
			}
		}

		subDishList.getOverlist().remove(li);
		Getstatic.setSubDishList(manageId, subDishList);
		in.setCode("1");
		in.setMessage("完成成功!");
		
		return in;
	}
	
}
