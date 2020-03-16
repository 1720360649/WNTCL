package cn.newtcl.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.newtcl.entity.Information;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Orderline;
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
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

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
				
				List<subDish> temp = ((Orders)session.getAttribute("orders")).getSubdishs();
				if(temp != null && temp.size() != 0){
					for (subDish subDish : temp) {
						subDish.setOrderid(order.getId());
						subDish.setTable(order.getTable());
					}
					subDishList.addList(temp);
				}
			}
			
			order.setSubdishs(null);
			order.setTotal(0.00);
			session.setAttribute("orders",order);
			session.setAttribute("orderlist",null);
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
		}
		
		return in;
	}
	
	@RequestMapping("vueadd")
//	public @ResponseBody String vueAdd(Integer table,Double total,String describable,Integer managerId,String[][] subdishs) {
	public @ResponseBody boolean vueAdd(@RequestBody String str) {
		NewReturn re = new NewReturn();
		Orders order = new Orders();
		List<subDish> subdishs = new ArrayList<subDish>();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			JsonNode node = mapper.readTree(str);
			order.setTable(node.get("table").asInt());
			order.setTotal(node.get("total").asDouble());
			order.setDescribable(node.get("describable").asText());
			order.setManagerId(node.get("managerId").asInt());
			//遍历subdishs
			JsonNode subdishsnode = node.path("subdishs");
			for(int i=0;i<subdishsnode.size();i++){
				subDish sub = new subDish();
				JsonNode temp = subdishsnode.get(i);
				sub.setId(temp.get("id").asInt());
				sub.setNumber(temp.get("number").asInt());
				sub.setPrice(temp.get("price").asDouble());
				subdishs.add(sub);
			}
			order.setSubdishs(subdishs);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if (subdishs == null || subdishs.size() < 1) {
			return false;
		}else if(order.getTable() == null || order.getTable() == 0){
			return false;
		} else if(session.getAttribute("wntcluser") == null){
			return false;
		}else{
			//事务
			//1.获取事务定义
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			//2.设置事务隔离级别，开启新事务
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			//3.获得事务状态
			TransactionStatus status = transactionManager.getTransaction(def);
			
			//添加默认项
			SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = da.format(new Date());
			order.setTime(date);
			order.setMakestatus(0);
			order.setDeliverystatus(0);
			order.setUserId(((User)session.getAttribute("wntcluser")).getId());
			
			re = imOrderService.add(order);
			if (re.getCode().equals("1")) {
				int id = (int)re.getObj();
				order.setId(id);
				NewReturn r = new NewReturn();
				r = imOrderlineService.add(order);
				if(("1").equals(r.getCode())){
					// 事务提交
					transactionManager.commit(status);
					return true;
				}else{
					// 事务回滚
					transactionManager.rollback(status);
					return false;
				}
			}else{
				// 事务回滚
				transactionManager.rollback(status);
				return false;
			}
		}
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
	
	@RequestMapping("findtouser")
	public @ResponseBody List<Orders> findToUser() {
		NewReturn re = new NewReturn();
		Orders order = new Orders();
		List<Orders> list = null;
		User user = (User)session.getAttribute("wntcluser");
		
//		test
//		order.setUserId(1);
		
		if(user == null || user.getId() == null)
			return null;

		re = imOrderService.find(order);
		
		if(("1").equals(re.getCode()))
			list = (List<Orders>)re.getObj();
	
		return list;
	}

}
