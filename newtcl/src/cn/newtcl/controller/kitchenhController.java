package cn.newtcl.controller; 
 

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.newtcl.entity.Information;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.User;
import cn.newtcl.entity.subDish;
import cn.newtcl.service.impl.ImOrderlineService;

@Controller
@RequestMapping("kitchenhall")
public class kitchenhController{

	@Resource
	private ImOrderlineService imOrderlineService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/getkitchenhallnumber")
	public @ResponseBody Integer getkitchenhallnumber(){
		
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
	
		String id = GetUserId();
		//添加当前用户操作时间
		subDishList.setLasttime(id,new Date());
		
		return subDishList.GetListSize();
	}
	
	@RequestMapping("/getlist")
	public @ResponseBody List<subDish> getlist(){
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
		
		String id = GetUserId();
		ArrayList<subDish>  map= (ArrayList<subDish>) subDishList.getMap(id);
		int num = subDishList.getNum();
		List<subDish> ulist = null;
		
		if(map != null){
			
			ulist = subDishList.distributor(id);
			
			if(ulist == null || ulist.size() == 0){
				ulist = new ArrayList<subDish>();
				subDish sub = new subDish();
				sub.setNumber(0);
				ulist.add(sub);
			}
			
		}else{
			
			num++;
			subDishList.setNum(num);
			
			ulist = subDishList.distributor(id);
			
			if(ulist == null || ulist.size() == 0){
				ulist = new ArrayList<subDish>();
				subDish sub = new subDish();
				sub.setNumber(0);
				ulist.add(sub);
			}
		}
		
		
		//添加当前用户操作时间
		subDishList.setLasttime(id,new Date());
		Getstatic.setSubDishList(manageId, subDishList);
		return ulist;
	}
	
	@RequestMapping("/remove")
	public @ResponseBody Information remove(){
		Information in = new Information();
		String id = GetUserId();
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
		
		ArrayList<subDish> list = subDishList.getMap(id);
		
		Integer staff = Integer.valueOf(GetUserId());

		if(staff == null || staff < 1){
			in.setCode("0");
			in.setMessage("状态异常,请重新进入页面或重新登陆!");
		}else{
			if(list == null || list.size() < 1){
				in.setCode("0");
				in.setMessage("当前已无单,移除失败");
			}else{
				NewReturn re = imOrderlineService.changMakeStatus(list.get(0).getId(), 1, staff);
				if(re.getCode().equals("1")){
					
					int table = list.get(0).getTable();
					subDishList.setOverlist(table,list);
					list.remove(0);
					
					in.setCode("1");
					in.setMessage("已完成");
				}else{
					in.setCode("0");
					in.setMessage("未知异常请重试或联系管理员!");
				}
			}
		}
		
		subDishList.setLasttime(id, new Date());
		Getstatic.setSubDishList(manageId, subDishList);
		return in;
	}
	
	/***********************************通用用户id获取***************************************/
	private String GetUserId(){
		User user = (User)session.getAttribute("wntcluser");
		if(user == null || user.getId() == null)
			return null;
		else
			return String.valueOf(user.getId());

		//test
		//	return 1;

	}
	
}
