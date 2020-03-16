package cn.newtcl.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
@RequestMapping("orderline")
public class OrderlineController {

	//注入Service
	@Resource
	private ImOrderlineService imOrderlineService;
	
	@RequestMapping("add")
	public @ResponseBody Information add(@RequestBody Orders order){
		
		NewReturn re = new NewReturn();
		re = imOrderlineService.add(order);
		Information in = new Information();
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}
	
	@RequestMapping("delete")
	public @ResponseBody Information delete(@RequestBody Orders order){
		
		NewReturn re = new NewReturn();
		re = imOrderlineService.delete(order);
		Information in = new Information();
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}
	
	@RequestMapping("getOrderList")
	public @ResponseBody Information getOrderList(@RequestBody Orders order){
		
		NewReturn re = new NewReturn();
		re = imOrderlineService.getOrderList(order);
		Information in = new Information();
		if(re.getCode().equals("1")){
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
			
			List<subDish> temp = (List<subDish>)re.getObj();
			StringBuilder str = new StringBuilder();
			str.append("<div class='orderlines'><ul>");
			for(int i=0;i<temp.size();i++){
				subDish subdish = temp.get(i);
				str.append("<li class='orderline'>");
					//photo
					str.append("<div class='photo'>");
						str.append("<img src="+subdish.getPhoto()+"style='' />");
					str.append("</div>");
					//name
					str.append("<div class='name'> style='' ");
						str.append(subdish.getName());
					str.append("</div>");
					//price
					str.append("<div class='price'> style='' ");
						str.append(subdish.getPrice());
					str.append("</div>");
					//count
					str.append("<div class='count'> style='' ");
						str.append(subdish.getNumber());
					str.append("</div>");
				str.append("</li>");
			}
			str.append("</ul><div>");
			
			in.setHtml(str.toString());
		}else{
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
		}
		
		return in;
	}
	
	@RequestMapping("find")
	public @ResponseBody Information find(@RequestBody Map<String, Object> map){
		
		NewReturn re = new NewReturn();
		re = imOrderlineService.find(map);
		Information in = new Information();
		if(re.getCode().equals("1")){
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
			
		}else{
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
		}
		
		return in;
	}
	
	@RequestMapping("edit")
	public @ResponseBody Information edit(@RequestBody Orderline order){
		
		NewReturn re = new NewReturn();
		re = imOrderlineService.edit(order);
		Information in = new Information();
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}
	
	@RequestMapping("getOrderListforid")
	public @ResponseBody List<subDish> getOrderListForId(Integer id){
		Orders order = new Orders();
		order.setId(id);
		NewReturn re = new NewReturn();
		re = imOrderlineService.getOrderList(order);
		if(re.getCode().equals("1")){
			return (List<subDish>)re.getObj();
		}else{
			return null;
		}
	}

}
