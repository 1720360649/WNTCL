package cn.newtcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtcl.entity.Goods;
import cn.newtcl.entity.Information;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Orders;
import cn.newtcl.entity.Type;
import cn.newtcl.entity.TypeAndGoods;
import cn.newtcl.entity.User;
import cn.newtcl.entity.subDish;
import cn.newtcl.service.impl.ImGoodsService;
import cn.newtcl.service.impl.ImOrderService;
import cn.newtcl.service.impl.ImOrderlineService;
import cn.newtcl.service.impl.ImTypeService;
import cn.newtcl.service.impl.ImUserService;
import cn.newtcl.utils.shopPhotoUp;

@Controller
@RequestMapping("supporter")
public class SupporterController {
	
	@Autowired
	private ImUserService imUserService;
	@Autowired
	private ImOrderService imOrderService;
	@Autowired
	private ImOrderlineService imOrderlineService;
	@Autowired 
	private ImGoodsService imGoodsService;
	@Autowired
	private ImTypeService imTypeService;

	@Autowired
	private HttpSession session;
	
	@RequestMapping("/getme")
	@ResponseBody
	public User getMe(){
		User user = (User)session.getAttribute("wntcluser");
		if(user == null || user.getId() == null){
			return null;
		}
	
		//出于安全考虑,虽然已加密但仍对账号密码和支付密码进行清除
		user.setPassword("0");
		user.setPayPassword("0");
	
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

	@RequestMapping("/getmeshop")
	@ResponseBody
	public Information getMeShop(){
		Information in =new Information();
		NewReturn re = new NewReturn();	
		Type type = new Type();
		Map<Integer, List<TypeAndGoods>> map = new HashMap<Integer, List<TypeAndGoods>>();
		
		if(GetmanageId() !=null){
			type.setManagerId(GetmanageId());
		}else{
			in.setCode("-1");
			in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
			return in;
		}

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
						
						if(good.getOldprice() == null || good.getOldprice() <= 0){
							main.append("<div class=\"oldpice\">原价:<input type=\"text\" value=\"无\""
								+"onclick=\"goodsedit(this,'oldpice')\" onblur=\"goodseditlast(this,'oldpice')\" style=\"color:gray;\"></div>");
						}else{
							main.append("<div class=\"oldpice\">原价:<input type=\"number\" value='"+good.getOldprice()+"'"
								+"onclick=\"goodsedit(this,'oldpice')\" onblur=\"goodseditlast(this,'oldpice')\"></div>");
						}
						
						if(good.getStock() == null || good.getStock() <= 0){
							main.append("<div class=\"goodstock\">库存:<input type=\"text\" value='无'"
								+"onclick=\"goodsedit(this,'goodstock')\" onblur=\"goodseditlast(this,'goodstock')\" style=\"color:gray;\">"
								+"</div>");
						}else{
							main.append("<div class=\"goodstock\">库存:<input type=\"number\" value='"+(good.getStock()-1)+"'"
						+"onclick=\"goodsedit(this,'goodstock')\" onblur=\"goodseditlast(this,'goodstock')\">"
						+"</div>");
						}
					
						if(good.getStatus() == 1){
							main.append("<div class=\"goodseditbut goodseditbutdown\" onclick=\"goodedit("+good.getGoodsId()+",0)\">下架</div><div class=\"goodsaddover\" onclick=\"gooddelete("+good.getGoodsId()+")\">删除</div>");
						}else{
							main.append("<div class=\"goodseditbut goodseditbutup\" onclick=\"goodedit("+good.getGoodsId()+",1)\">上架</div><div class=\"goodsaddover\" onclick=\"gooddelete("+good.getGoodsId()+")\">删除</div>");
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
	
	
	@RequestMapping("/addtype")
	@ResponseBody
	public Information addtype(String name){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Type type = new Type();
		
		if(name != null){
			if(GetmanageId() !=null){
				type.setManagerId(GetmanageId());
			}else{
				in.setCode("-1");
				in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
				return in;
			}
		
			type.setName(name);
			re = imTypeService.add(type);
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
		}else{
			in.setCode("0");
			in.setMessage("提交数据不完整,请勿非法操作!");
		}
		
		return in;
	}
	
	@RequestMapping("/deltype")
	@ResponseBody
	public Information deltype(Integer id){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Type type = new Type();
		
		if(id != null){
			if(GetmanageId() !=null){
				type.setManagerId(GetmanageId());
			}else{
				in.setCode("-1");
				in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
				return in;
			}
		
			type.setId(id);
			re = imTypeService.delete(type);
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
		}else{
			in.setCode("0");
			in.setMessage("提交数据不完整,请勿非法操作!");
		}
		
		return in;
	}
	
	@RequestMapping("/findtype")
	@ResponseBody
	public Information findtype(){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Type type = new Type();
		boolean def= false;
		StringBuilder aside = new StringBuilder();
		aside.append("<div id='shop_left'><aside id='shop_aside'>");
		
		if(GetmanageId() !=null){
			type.setManagerId(GetmanageId());
		}else{
			in.setCode("-1");
			in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
			return in;
		}
		

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
	
	@RequestMapping("/addgood")
	@ResponseBody
	public Information addGood(String name,Integer nowprice,Integer oldprice,Integer stock,String photo,Integer typeId){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Goods good = new Goods();
		
		if(name != null && nowprice != null && photo != null && oldprice != null && stock != null && typeId !=null){
			
			if(GetmanageId() !=null){
				good.setManagerId(GetmanageId());
			}else{
				in.setCode("-1");
				in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
				return in;
			}
			
			good.setName(name);
			good.setNowprice((double)nowprice);
			good.setOldprice((double)oldprice);
			good.setStock(stock);
			good.setPhoto(photo);
			good.setTypeId(typeId);
			
			re = imGoodsService.add(good);
	
			in.setCode(re.getCode());
			in.setMessage(re.getMessage());
			
		}else{
			in.setCode("0");
			in.setMessage("提交数据不完整,请勿非法操作!");
		}
		return in;
	}
	
	@RequestMapping("/editgood")
	@ResponseBody
	public Information editGood(Integer id,Integer status){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Goods goods = new Goods();
		
		if(id != null && status !=null && (status == 1 || status == 0)){
			
			if(GetmanageId() !=null){
				goods.setManagerId(GetmanageId());
			}else{
				in.setCode("-1");
				in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
				return in;
			}
			
			goods.setId(id);
			goods.setStatus(status);
			
			re = imGoodsService.update(goods);
			if("1".equals(re.getCode())){
				in.setCode("1");
				if(status == 1){
					in.setMessage("商品上架成功");
				}else{
					in.setMessage("商品下架成功");
				}
			}else{
				in.setCode("0");
				in.setMessage(re.getMessage());
			}
		}else{
			in.setCode("0");
			in.setMessage("提交数据不完整,请勿非法操作!");
		}
	
		return in;
	}
	
	@RequestMapping("/deletegood")
	@ResponseBody
	public Information deleteGood(Integer id){
		Information in =new Information();
		NewReturn re = new NewReturn();
		Goods goods = new Goods();
		
		if(id != null && id>0){
		
			if(GetmanageId() !=null){
				goods.setManagerId(GetmanageId());
			}else{
				in.setCode("-1");
				in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
				return in;
			}
			
			goods.setId(id);
			
			re = imGoodsService.delete(goods);
			if("1".equals(re.getCode())){
				in.setCode("1");
				in.setMessage("商品删除成功");
			}else{
				in.setCode("0");
				in.setMessage(re.getMessage());
			}
		}else{
			in.setCode("0");
			in.setMessage("提交数据不完整,请勿非法操作!");
		}

		return in;
	}
	
	@RequestMapping("/getstaff")
	@ResponseBody
	public List<User> getStaff(){
		Information in =new Information();
		NewReturn re = new NewReturn();
		User user = new User();
		
			if(GetmanageId() !=null){
				user.setManagerId(GetmanageId());
			}else{
				in.setCode("-1");
				in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
				return null;
			}
			
			re = imUserService.find(user);
			if("1".equals(re.getCode())){
				List<User> list = (List<User>)re.getObj();
				//出于安全考虑,虽然已加密但仍对账号密码和支付密码进行清除
				for(int i = 0;i<list.size();i++){
					User temp= list.get(i);
					temp.setPassword("0");
					temp.setPayPassword("0");
				}
				return list;
			}else{
				return null;
			}
	}
	
	@RequestMapping("/getstaffanalysis")
	@ResponseBody
	public List<subDish> getStaffAnalysis(){
		if(GetmanageId() == null){
			return null;
		}
		return imOrderlineService.getStaffAnalysis(GetmanageId());
	}
	
	@RequestMapping("/getstaffanalysisone")
	@ResponseBody
	public List<subDish> getstaffAnalysisOne(Integer staffid){
		if(GetmanageId() == null || staffid == null){
			return null;
		}
		return imOrderlineService.getStaffAnalysisOne(GetmanageId(),staffid);
	}
	
	@RequestMapping("/getorderlineanalysisforgoods")
	@ResponseBody
	public List<subDish> getorderlineAnalysisForGoods(){
		if(GetmanageId() == null){
			return null;
		}
		return imOrderlineService.getorderlineAnalysisForGoods(GetmanageId());
	}
	
	@RequestMapping("/getorderlineanalysisfortype")
	@ResponseBody
	public List<subDish> getorderlineAnalysisForType(){
		if(GetmanageId() == null){
			return null;
		}
		return imOrderlineService.getorderlineAnalysisForType(GetmanageId());
	}

	
	/***********************************通用商家id获取***************************************/
	private Integer GetmanageId(){
//		User user = (User)session.getAttribute("wntcluser");
//		if(user == null || user.getId() == null)
//			return null;
//		else
//			return user.getId();
	
	//test
	return 1;
		
	}
	
	
}
