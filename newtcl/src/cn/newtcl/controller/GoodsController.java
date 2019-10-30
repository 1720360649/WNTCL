package cn.newtcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtcl.entity.Information;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Type;
import cn.newtcl.entity.TypeAndGoods;
import cn.newtcl.service.impl.ImTypeService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	
	@Autowired
	ImTypeService imTypeService;
	
	@RequestMapping("showgoodstotype")
	@ResponseBody
	public Information showGoodstoType(Integer manager_id){
		Information in = new Information();
		Map<Integer, List<TypeAndGoods>> map = new HashMap<Integer, List<TypeAndGoods>>();
		Type type = new Type();
		boolean def= false;
		int line = 0;
		
		if(manager_id == null){
			in.setCode("-1");
			in.setMessage("未给出商家id");
		}
		type.setManagerId(manager_id);
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
						aside.append("<div class='menuType menuActive' onclick='gottype(this,0)'>"+list.get(0).getTyupeName()+"</div>");
						if(list.size()%2 != 0){
							line += ((list.size()+1)/2);
						}else{
							line += ((list.size()+1)/2);
						}
						def = true;
					}else{
						aside.append("<div class='menuType' onclick='gottype(this,"+line+")'>"+list.get(0).getTyupeName()+"</div>");
						if(list.size()%2 != 0){
							line += ((list.size()+1)/2);
						}else{
							line += ((list.size()+1)/2);
						}
					}
					for(int i=0;i<list.size();i++){
						TypeAndGoods good = list.get(i);
						main.append("<div class=\"goodsshow_good\"><div class=\"goodsshow_good_img\"> <img"
									+"src=\"/newtcl"+good.getPhoto()+"\"></div>" +"<div class=\"goodsshow_good_name\">"
                                    +good.getGoodsName()+"</div><div class=\"goodsshow_good_price\">¥"
                                    +good.getNowprice()+"<div class=\"goodsshow_good_add\"onclick=\"goodsshow_good_add("
                                    +good.getGoodsId()+")\"><img src=\"/newtcl/img/add.png\"/></div></div></div>");
					}
					if(list.size()%2 != 0){
						main.append("<div class='goodsshow_good' style='border-color:white;'></div>");
					}
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
