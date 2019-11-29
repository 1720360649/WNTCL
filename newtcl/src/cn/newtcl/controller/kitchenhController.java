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
		Map<String, Date> time = subDishList.getLasttime();
		//添加当前用户操作时间
		time.put(id, new Date());
		subDishList.setLasttime(time);
		
		return subDishList.getList().size();
	}
	
	@RequestMapping("/getlist")
	public @ResponseBody List<subDish> getlist(){
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
		
		String id = GetUserId();
		Map<String, ArrayList<subDish>> map= subDishList.getMap();
		Map<String, Date> time = subDishList.getLasttime();
		int num = subDishList.getNum();
		List<subDish> ulist = null;
		
		if(map.containsKey(id)){
			
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
		time.put(id, new Date());
		subDishList.setLasttime(time);
		Getstatic.setSubDishList(manageId, subDishList);
		return ulist;
	}
	
	@RequestMapping("/remove")
	public @ResponseBody Information remove(){
		Information in = new Information();
		String id = GetUserId();
		Integer manageId = (Integer)session.getAttribute("managerid");
		Getstatic subDishList = Getstatic.getSubDishList(manageId);
		
		Map<String, ArrayList<subDish>> map= subDishList.getMap();
		Map<String, Date> time = subDishList.getLasttime();
		ArrayList<subDish> list = map.get(id);
		
		Integer staff = Integer.valueOf(GetUserId());

		if(staff == null || staff < 1){
			in.setCode("0");
			in.setMessage("状态异常,请重新进入页面或重新登陆!");
		}else{
			if(list.size() < 1){
				in.setCode("0");
				in.setMessage("当前已无单,移除失败");
			}else{
				NewReturn re = imOrderlineService.changMakeStatus(list.get(0).getId(), 1, staff);
				if(re.getCode().equals("1")){
					
					int table = list.get(0).getTable();
					Map<Integer,List<subDish>> temp = subDishList.getOverlist();
					if(temp == null || temp.size() <= 0)
						temp = new HashMap<Integer, List<subDish>>();
						
					if(temp.containsKey(table)){
						List<subDish> tempsub = temp.get(table);
						tempsub.add(list.get(0));
						temp.put(table, tempsub);
					}else{
						List<subDish> tempsub = new ArrayList<subDish>();
						tempsub.add(list.get(0));
						temp.put(table, tempsub);
					}
					
					subDishList.setOverlist(temp);
					list.remove(0);
					map.put(id, list);
					subDishList.setMap(map);
					
					
					in.setCode("1");
					in.setMessage("已完成");
				}else{
					in.setCode("0");
					in.setMessage("未知异常请重试或联系管理员!");
				}
			}
		}
		
		time.put(id, new Date());
		subDishList.setLasttime(time);
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
