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
		Type type = new Type();
		Map<Integer, List<TypeAndGoods>> map = new HashMap<Integer, List<TypeAndGoods>>();
		boolean def= false;
		
		User user = (User)session.getAttribute("wntcluser");
		if(user == null || user.getId() == null){
			in.setCode("-1");
			in.setMessage("获取登陆信息失败!请重新登陆或联系管理员!");
			return in;
		}
		
		type.setManagerId(user.getId());
		NewReturn re = imTypeService.findTypeAndGoods(type);
		if(("1").equals(re.getCode())){
			map = (Map<Integer, List<TypeAndGoods>>)re.getObj();
			StringBuilder aside = new StringBuilder();
			StringBuilder main  = new StringBuilder();
			aside.append("<aside id='aside'>");
			main.append("<div id='main'>");
			
			for (Entry<Integer, List<TypeAndGoods>> entry : map.entrySet()) {
				List<TypeAndGoods> list = entry.getValue();
				if(list != null && list.size()>0){
					//设置分类标签
					if(!def){
						aside.append("<div class='menuType menuActive' onclick='gototype('a"+list.get(0).getTypeId()+"')'>"+list.get(0).getTyupeName()+"</div>");
						def = true;
					}else{
						aside.append("<div class='menuType' onclick='gototype('a"+list.get(0).getTypeId()+"')'>"+list.get(0).getTyupeName()+"</div>");
					}
					
					main.append("<div id='t"+list.get(0).getTypeId()+"'>");
					for(int i=0;i<list.size();i++){
						TypeAndGoods good = list.get(i);
						main.append("<div class=\"goodsshow_good\"><div class=\"goodsshow_good_img\"> <img"
									+" src=\"/newtcl"+good.getPhoto()+"\"></div>" +"<div class=\"goodsshow_good_name\">"
                                    +good.getGoodsName()+"</div><div class=\"goodsshow_good_price\">¥"
                                    +good.getNowprice()+"<div class=\"goodsshow_good_add\"onclick=\"goodsshow_good_add("
                                    +good.getGoodsId()+")\"><img src=\"/newtcl/img/add.png\"/></div></div></div>");
					}
					main.append("<div class='goodsshow_good_add'></div>");
					main.append("</div>");
				}else{
					main.append("<div>此分类暂无商品</div>");
				}
			}
			aside.append("</aside>");
			main.append("</div>");
			in.setCode("1");
			in.setMessage("查询到数据!");
			in.setHtml(aside.toString()+main.toString());
		}else{
			in.setCode("0");
			in.setMessage("未查询到数据!");
		}
		
		return in;
	}
	
}
