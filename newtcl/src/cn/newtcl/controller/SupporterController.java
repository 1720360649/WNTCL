package cn.newtcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtcl.entity.Information;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Orders;
import cn.newtcl.entity.Type;
import cn.newtcl.entity.TypeAndGoods;
import cn.newtcl.entity.User;
import cn.newtcl.service.impl.ImGoodsService;
import cn.newtcl.service.impl.ImOrderService;
import cn.newtcl.service.impl.ImOrderlineService;
import cn.newtcl.service.impl.ImTypeService;
import cn.newtcl.service.impl.ImUserService;

@Controller
@RequestMapping("supporter")
public class SupporterController {
	
	@Autowired
	ImUserService imUserService;
	@Autowired
	ImOrderService imOrderService;
	@Autowired
	ImOrderlineService imOrderlineService;
	@Autowired 
	ImGoodsService imGoodsService;
	@Autowired
	ImTypeService imTypeService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/getme")
	@ResponseBody
	public User getMe(){
		User user = (User)session.getAttribute("wntcluser");
		if(user == null || user.getId() == null){
			return null;
		}
	
		user.setPassword("0");
		user.setPayPassword(0);
		
		return user;
	}
	
	@RequestMapping("/businessanalysis")
	@ResponseBody
	public List<Orders> BusinessAnalysis(){
		List<Orders> list = null;
		User user = (User)session.getAttribute("wntcluser");
		if(user == null || user.getId() == null){
			return null;
		}
		list = imOrderService.BusinessAnalysis(user.getId());
		if(list == null || list.size() < 1){
			return null;
		}
		return list;
	}
	
	@RequestMapping("getmeshop")
	@ResponseBody
	public Information getMeShop(){
		Information in =new Information();
		NewReturn re = new NewReturn();	
		Type type = new Type();
		Map<Integer, List<TypeAndGoods>> map = new HashMap<Integer, List<TypeAndGoods>>();
		
//		User user = (User)session.getAttribute("wntcluser");
//		if(user == null || user.getId() == null){
//			in.setCode("-1");
//			in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
//			return in;
//		}
		
		//type.setManagerId(user.getId());
		
		//test
		type.setManagerId(1);
		//加载标签
		re = imTypeService.find(type);
		List<Type> types = (List<Type>)re.getObj();
		//加载商品
		re = imTypeService.findTypeAndGoods(type);
		if(("1").equals(re.getCode())){
			map = (Map<Integer, List<TypeAndGoods>>)re.getObj();
			StringBuilder main  = new StringBuilder();			
			for (Entry<Integer, List<TypeAndGoods>> entry : map.entrySet()) {
				List<TypeAndGoods> list = entry.getValue();
				if(list != null && list.size()>0){
					main.append("<div class=\"typebox\" id='t"+list.get(0).getTypeId()+"'><ul>");
					main.append("<li class=\"addgood\" onclick=\"addgoods(this,"+list.get(0).getTypeId()+")\">+</li>");
					for(int i=0;i<list.size();i++){
						TypeAndGoods good = list.get(i);
						main.append("<li><img src=\"/newtcl"+good.getPhoto()+"\">"
						+"<input class=\"goodsname\" type=\"text\" readonly=\"readonly\" value=\""+good.getGoodsName()+"\""
						+"onclick=\"goodsedit(this,'name')\" onblur=\"goodseditlast(this,'name')\">"
						+"<div class=\"nowpice\">现价:<input type=\"number\" value='"+good.getNowprice()+"'"
						+"onclick=\"goodsedit(this,'nowpice')\" onblur=\"goodseditlast(this,'nowpice')\"></div>");
						
						if(good.getOldprice() == null || good.getOldprice() < 0){
							main.append("<div class=\"oldpice\">原价:<input type=\"text\" value=\"无\""
								+"onclick=\"goodsedit(this,'oldpice')\" onblur=\"goodseditlast(this,'oldpice')\" style=\"color:gray;\"></div>");
						}else{
							main.append("<div class=\"oldpice\">原价:<input type=\"number\" value='"+good.getOldprice()+"'"
								+"onclick=\"goodsedit(this,'oldpice')\" onblur=\"goodseditlast(this,'oldpice')\"></div>");
						}
						
						if(good.getStock() == null || good.getStock() < 0){
							main.append("<div class=\"goodstock\">库存:<input type=\"text\" value='无'"
								+"onclick=\"goodsedit(this,'goodstock')\" onblur=\"goodseditlast(this,'goodstock')\" style=\"color:gray;\">"
								+"</div>");
						}else{
							main.append("<div class=\"goodstock\">库存:<input type=\"number\" value='"+good.getStock()+"'"
						+"onclick=\"goodsedit(this,'goodstock')\" onblur=\"goodseditlast(this,'goodstock')\">"
						+"</div>");
						}
						
						if(good.getStatus() == 1){
							main.append("<div class=\"goodseditbut\" onclick=\"\">下架</div>");
						}else{
							main.append("<div class=\"goodseditbut\" onclick=\"\">上架</div>");
						}
						main.append("</li>");
					}
					main.append("</ul></div>");
				}
			}
			if(map.size() < types.size()){
				for(int i=(map.size()-1);i<types.size();i++){
					main.append("<div class=\"typebox\" id='t"+types.get(i).getId()+"'><ul>");
					main.append("<li class=\"addgood\" onclick=\"addgoods(this,"+types.get(i).getId()+")\">+</li>");
					main.append("</ul></div>");
				}
			}
	
			main.append("</div>");
			in.setCode("1");
			in.setMessage("查询到数据!");
			in.setHtml(main.toString());
		}else{
			in.setCode("0");
			in.setMessage("未查询到数据!");
		}
		
		return in;
	}
	
	
	@RequestMapping("addtype")
	@ResponseBody
	public Information addtype(String name){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Type type = new Type();
		
//		User user = (User)session.getAttribute("wntcluser");
//		if(user == null || user.getId() == null){
//			in.setCode("-1");
//			in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
//			return in;
//		}
		
		//type.setManagerId(user.getId());
		
		//test
		type.setManagerId(1);
		
		type.setName(name);
		re = imTypeService.add(type);
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}
	
	@RequestMapping("deltype")
	@ResponseBody
	public Information deltype(Integer id){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Type type = new Type();
		
//		User user = (User)session.getAttribute("wntcluser");
//		if(user == null || user.getId() == null){
//			in.setCode("-1");
//			in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
//			return in;
//		}
		
		//type.setManagerId(user.getId());
		
		//test
		type.setManagerId(1);
		
		type.setId(id);
		re = imTypeService.delete(type);
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}
	
	@RequestMapping("findtype")
	@ResponseBody
	public Information findtype(){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Type type = new Type();
		boolean def= false;
		StringBuilder aside = new StringBuilder();
		aside.append("<div id='shop_left'><aside id='shop_aside'>");
		
//		User user = (User)session.getAttribute("wntcluser");
//		if(user == null || user.getId() == null){
//			in.setCode("-1");
//			in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
//			return in;
//		}
		
		//type.setManagerId(user.getId());
		
		//test
		type.setManagerId(1);
		re = imTypeService.find(type);
		
		List<Type> list = (List<Type>)re.getObj();
		if(list != null || list.size() > 0 ){
			for(int i=0;i<list.size();i++){
				if(!def){
					aside.append("<div class='type typeActive' onclick=\"ontype(this,'#t"+list.get(i).getId()+"')\" oncontextmenu=\"typesmenu(this)\">"+list.get(i).getName()+"<div class=\"deltype\" onclick=\"deltype(this,"+list.get(i).getId()+")\">删除</div></div>");
					def = true;
				}else{
					aside.append("<div class='type' onclick=\"ontype(this,'#t"+list.get(i).getId()+"')\" oncontextmenu=\"typesmenu(this)\">"+list.get(i).getName()+"<div class=\"deltype\" onclick=\"deltype(this,"+list.get(i).getId()+")\">删除</div></div>");
				}
			}
		}
	
		aside.append("<div class=\"addtypes\" style=\"border:2px dashed white;\" onclick=\"onaddtypes()\">+</div>");
		aside.append("</aside></div>");
		in.setHtml(aside.toString());
		
		return in;
	}
	
	@RequestMapping("addgood")
	@ResponseBody
	public Information addGood(String name){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Type type = new Type();
		
//		User user = (User)session.getAttribute("wntcluser");
//		if(user == null || user.getId() == null){
//			in.setCode("-1");
//			in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
//			return in;
//		}
		
		//type.setManagerId(user.getId());
		
		//test
		type.setManagerId(1);
		
		type.setName(name);
		re = imTypeService.add(type);
		in.setCode(re.getCode());
		in.setMessage(re.getMessage());
		return in;
	}
	
}
