package cn.newtcl.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import cn.newtcl.entity.Orders;
import cn.newtcl.entity.User;
import cn.newtcl.entity.subDish;
import cn.newtcl.service.impl.ImOrderService;
import cn.newtcl.service.impl.ImOrderlineService;

@Controller
@RequestMapping("order")
public class OrderController {
	/**
	 * 注入service
	 */
	@Resource
	private ImOrderService imOrderService;
	@Resource
	private ImOrderlineService imOrderlineService;
	
	@Autowired
	private HttpSession session;

	@RequestMapping("add")
	public @ResponseBody Information add() {
		Orders order;
		Information in = new Information();
		
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
		
		if (session.getAttribute("orders") == null) {
			order = new Orders();
		} else {
			order = (Orders) session.getAttribute("orders");
		}
		
		List<subDish> lists = order.getSubdishs();
		
		if (lists == null || lists.size() < 1) {
			in.setCode("0");
			in.setMessage("无效订单,请重新操作");
		}else if(order.getTable() == null || order.getTable() == 0){
			in.setCode("0");
			in.setMessage("未知桌号,请重新扫码!");
		} else if(session.getAttribute("managerid") == null){
			in.setCode("0");
			in.setMessage("未知商户,请重新扫码!");
		}else{

			NewReturn re = new NewReturn();
			SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = da.format(new Date());
			order.setTime(date);
			order.setMakestatus(0);
			order.setDeliverystatus(0);
			order.setDescribable("未完善");
			order.setUserId(((User)session.getAttribute("wntcluser")).getId());
			
			// 商户id
			order.setManagerId((Integer)session.getAttribute("managerid"));
			
			

			re = imOrderService.add(order);
			if (re.getCode().equals("1")) {
				
				int id = (int)re.getObj();
				order.setId(id);
				NewReturn r = new NewReturn();
				r = imOrderlineService.add(order);
				re.setCode(r.getCode());
				re.setMessage(re.getMessage()+r.getMessage());
				
				List<subDish> list = subDishList.getList();
				if(list == null){
					list = new ArrayList<subDish>();
				}
				List<subDish> temp = ((Orders)session.getAttribute("orders")).getSubdishs();
				if(temp != null && temp.size() != 0){
					for (int i = 0;i<temp.size();i++){
						subDish dish = temp.get(i);
						dish.setOrderid(id);
						list.add(dish);
					}
				}
				// 值回设
				if(list!=null){
					subDishList.setList(list);
				}
			}
			
			order.setSubdishs(null);
			order.setTotal(0.00);
			session.setAttribute("orders",order);
			session.setAttribute("orderlist",null);
			Getstatic.setSubDishList(manageId, subDishList);
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
		}
		
		return in;
	}

	@RequestMapping("del")
	public @ResponseBody Information del(@RequestBody Orders order) {

		NewReturn re = new NewReturn();
		re = imOrderService.delete(order);
		Information in = new Information();
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}

	@RequestMapping("edit")
	public @ResponseBody Information edit(@RequestBody Orders order) {

		NewReturn re = new NewReturn();
		re = imOrderService.edit(order);
		Information in = new Information();
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}

	@RequestMapping("find")
	public @ResponseBody Information find(@RequestBody Orders order) {

		NewReturn re = new NewReturn();
		re = imOrderService.find(order);
		Information in = new Information();
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}

}
