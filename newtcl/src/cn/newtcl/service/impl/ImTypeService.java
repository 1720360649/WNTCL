package cn.newtcl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtcl.controller.GoodsController;
import cn.newtcl.dao.TypeMapper;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Type;
import cn.newtcl.entity.TypeAndGoods;
import cn.newtcl.service.TypeService;

@Service
public class ImTypeService implements TypeService {
	
	@Autowired
	TypeMapper typeMapper;
	

	@Override
	public NewReturn find(Type type) {
		NewReturn re = new NewReturn();
		if(type.getId() == null)
			if(type.getName() == null)	
				if(type.getManagerId() == null){
					re.setCode("-1");
					re.setMessage("未传入查询参数");
					return re;
				}
	
		List<Type> list = typeMapper.find(type);
		if(list !=null && list.size()>0){
			re.setCode("1");
			re.setMessage("查询到数据!");
			re.setObj(list);
		}else{
			re.setCode("0");
			re.setMessage("未查询到数据!");
		}
		return re;
	}

	@Override
	public Integer count(Type type) {
		if(type.getManagerId() == null || type.getId() == null){
			return -1;
		}
		
		return typeMapper.count(type);
	}

	@Override
	public NewReturn add(Type type) {
		NewReturn re = new NewReturn();
		if(type.getName() == null || type.getManagerId() == null){
			re.setCode("-1");
			re.setMessage("有未填必填项!");
			return re ;
		}
		int temp = typeMapper.add(type);
		if(temp>0){
			re.setCode("1");
			re.setMessage("添加成功!");
		}else{
			re.setCode("0");
			re.setMessage("添加失败!");
		}
		return re;
	}

	@Override
	public NewReturn update(Type type) {
		NewReturn re = new NewReturn();
		if(type.getId() == null){
			re.setCode("-1");
			re.setMessage("未给出主键 id");
			return re; 
		}
		int temp = typeMapper.update(type);
		if(temp > 0){
			re.setCode("1");
			re.setMessage("类别信息更新成功!");
		}else{
			re.setCode("0");
			re.setMessage("类别信息更新失败!");
		}
		return re;
	}

	@Override
	public NewReturn delete(Type type) {
		NewReturn re = new NewReturn();
		if(type.getId() == null){
			re.setCode("-1");
			re.setMessage("未给出主键 id");
			return re ;
		}
		int temp = typeMapper.delete(type);
		if(temp > 0){
			re.setCode("1");
			re.setMessage("类别删除成功!");
		}else{
			re.setCode("0");
			re.setMessage("类别删除失败!");
		}
		return re;
	}

	@Override
	public NewReturn findTypeAndGoods(Type type) {
		NewReturn re = new NewReturn();
		List<TypeAndGoods> list = new ArrayList<TypeAndGoods>();
		Map<Integer, List<TypeAndGoods>> map =new HashMap<Integer, List<TypeAndGoods>>();
		
		if(type.getManagerId() == null){
			re.setCode("-1");
			re.setMessage("未给出查询条件 managerId");
			return re;
		}
		
		list = typeMapper.findTypeAndGoods(type);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				TypeAndGoods tg = list.get(i);
				Integer key = tg.getTypeId();
				if(map.containsKey(key)){
					List<TypeAndGoods> temp = map.get(key);
					temp.add(tg);
					map.put(key, temp);
				}else{
					List<TypeAndGoods> temp = new ArrayList<TypeAndGoods>();
					temp.add(tg);
					map.put(key, temp);
				}
			}
			re.setCode("1");
			re.setMessage("查询到数据!");
			re.setObj(map);
		}else{
			re.setCode("0");
			re.setMessage("未查询到数据!");
		}
		
		return re;
	}

}
