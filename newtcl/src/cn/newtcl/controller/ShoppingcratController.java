package cn.newtcl.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.mail.handlers.image_gif;

import cn.newtcl.entity.Goods;
import cn.newtcl.entity.Information;
import cn.newtcl.entity.Orders;
import cn.newtcl.entity.subDish;
import cn.newtcl.service.GoodsService;
import cn.newtcl.service.impl.ImGoodsService;

@Controller
@RequestMapping("shoppingcrat")
public class ShoppingcratController{
	/**
	 * 注入session
	 */
	@Autowired
	private HttpSession session;
	
	@Autowired
    private ImGoodsService imGoodsService;
	
	@RequestMapping("/add")
	public @ResponseBody Information add(Integer id){
		Information in = new Information();
		Orders order;
		Map<Integer, subDish> orderlist;
		Integer managerid = null;
		
		if(session.getAttribute("managerid") == null){
			in.setCode("-1");
			in.setMessage("请重新进入页面");
			return in;
		}else{
			managerid = (Integer)session.getAttribute("managerid");
		}
		
		if(session.getAttribute("orders") == null){
			 order = new Orders();
			 order.setTotal(0.00);
		}else{
			 order = (Orders)session.getAttribute("orders");
			 if(order.getTotal() == null){
				 order.setTotal(0.00);
			 }
		}
		
		if(session.getAttribute("orderlist") == null){
			 orderlist = new HashMap<Integer, subDish>();
		}else{
			 orderlist = (Map<Integer,subDish>)session.getAttribute("orderlist");
		}
		
		if(orderlist.containsKey(id)){
			subDish subdish = orderlist.get(id);
			subdish.setNumber(subdish.getNumber()+1);
			orderlist.put(id,subdish);
			
			//将map中的菜遍历至 order
			List<subDish> list = new ArrayList<subDish>();
			Iterator<Integer> itr = orderlist.keySet().iterator();
			while(itr.hasNext()){
				int temp = itr.next();
				list.add(orderlist.get(temp));
			}
			order.setSubdishs(list);
			
			order.setTotal(order.getTotal()+subdish.getNowprice());
		}else{
			Goods good = new Goods();
			good = imGoodsService.findById(id,managerid);
			subDish subdish = new subDish();
			subdish.setId(good.getId());
			subdish.setName(good.getName());
			subdish.setDescribable(good.getDescribable());
			subdish.setNowprice(good.getNowprice());
			subdish.setOldprice(good.getOldprice());
			subdish.setPhoto(good.getPhoto());
			subdish.setManagerId(good.getManagerId());
			subdish.setStock(good.getStock());
			subdish.setPhoto(good.getPhoto());
			subdish.setTypeId(good.getTypeId());
			subdish.setNumber(1);
			subdish.setPrice(subdish.getNowprice());
			subdish.setTable(order.getTable());
			
			orderlist.put(id, subdish);
			
			//将map中的菜遍历至 order
			List<subDish> list = new ArrayList<subDish>();
			Iterator<Integer> itr = orderlist.keySet().iterator();
			while(itr.hasNext()){
				int temp = itr.next();
				list.add(orderlist.get(temp));
			}
			order.setSubdishs(list);
			
			order.setTotal(order.getTotal()+subdish.getNowprice());
		}
		
		
		session.setAttribute("orders", order);
		session.setAttribute("orderlist", orderlist);
		in.setCode("1");
		in.setMessage("操作完成");
		return in;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Information delete(Integer id){
		
		Orders order;
		Map<Integer, subDish> orderlist;
		Information in = new Information();
		
		if(session.getAttribute("orders") == null){
			 order = new Orders();
			 order.setTotal(0.00);
		}else{
			 order = (Orders)session.getAttribute("orders");
			 if(order.getTotal() == null){
				 order.setTotal(0.00);
			 }
		}
		
		if(session.getAttribute("orderlist") == null){
			 orderlist = new HashMap<Integer, subDish>();
		}else{
			 orderlist = (Map<Integer,subDish>)session.getAttribute("orderlist");
		}
		
		if(orderlist.containsKey(id)){
			subDish subdish = orderlist.get(id);
			if(subdish.getNumber() > 1){
				subdish.setNumber(subdish.getNumber()-1);
				orderlist.put(id, subdish);
				
				//将map中的菜遍历至 order
				List<subDish> list = new ArrayList<subDish>();
				Iterator<Integer> itr = orderlist.keySet().iterator();
				while(itr.hasNext()){
					int temp = itr.next();
					list.add(orderlist.get(temp));
				}
				order.setSubdishs(list);
				
				order.setTotal(order.getTotal()-subdish.getNowprice());
				
				in.setCode("1");
				in.setMessage("删除成功");
			}else{
				orderlist.remove(id);
				
				//将map中的菜遍历至 order
				List<subDish> list = new ArrayList<subDish>();
				Iterator<Integer> itr = orderlist.keySet().iterator();
				while(itr.hasNext()){
					int temp = itr.next();
					list.add(orderlist.get(temp));
				}
				order.setSubdishs(list);
				
				order.setTotal(0.00);
				
				in.setCode("1");
				in.setMessage("删除成功");
			}
			
		}else{
			in.setCode("0");
			in.setMessage("无此商品");
		}
		
		
		session.setAttribute("orders", order);
		session.setAttribute("orderlist", orderlist);
		
		return in;
		
	}
	
	/**
	 * 
	 * @param index
	 * 修改索引
	 * @param subdish
	 * 变更目标
	 */
	@RequestMapping("/edit")
	public void edit(@RequestBody int index,@RequestBody subDish subdish){
		
		Orders order;
		if(session.getAttribute("orders") == null){
			 order = new Orders();
		}else{
			 order = (Orders)session.getAttribute("orders");
		}
		
		List<subDish> list = order.getSubdishs();
		list.add(index,subdish);
		order.setSubdishs(list);
		session.setAttribute("orders", order);
	}
	
	@RequestMapping("/find")
	public @ResponseBody List<subDish> find(){
		
		Orders order;
		if(session.getAttribute("orders") == null){
			 order = new Orders();
		}else{
			 order = (Orders)session.getAttribute("orders");
		}
		List<subDish> lists = order.getSubdishs();
		if(lists == null || lists.size() < 1){
			lists = new ArrayList<subDish>();
			subDish subdish = new subDish();
			subdish.setNumber(0);
			lists.add(subdish);
		}
		
		return lists;
	}
}
