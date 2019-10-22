package cn.newtcl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtcl.dao.GoodsMapper;
import cn.newtcl.entity.Goods;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.service.GoodsService;

@Service
public class ImGoodsService implements GoodsService {
	
	@Autowired
	GoodsMapper goodsMapper;

	@Override
	public NewReturn find(Goods goods) {
		NewReturn re = new NewReturn();
		if(goods.getId() == null)
			if(goods.getName() == null)
				if(goods.getTypeId() == null)
					if(goods.getStatus() == null){
						re.setCode("-1");
						re.setMessage("未传入查询参数");
						return re;
					}
		
		List<Goods> list = goodsMapper.find(goods);
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
	public Integer count(Goods goods) {
		if(goods.getManagerId() == null || goods.getTypeId() == null){
			return -1;
		}
		
		return goodsMapper.count(goods);
	}

	@Override
	public NewReturn findByName(Goods goods) {
		NewReturn re = new NewReturn();
		if(goods.getName() == null){
			re.setCode("-1");
			re.setMessage("未给出name");
			return re;
		}
		Goods temp =new Goods();
		temp.setName(goods.getName());
		
		return find(temp);
	}

	@Override
	public NewReturn add(Goods goods) {
		NewReturn re = new NewReturn();
		if(goods.getName() == null || goods.getPhoto() == null 
				|| goods.getOldprice() == null || goods.getNowprice() == null 
				|| goods.getTypeId() == null || goods.getManagerId() == null){
								re.setCode("-1");
								re.setMessage("有未填必填项!");
								return re ;
							}
		int temp = goodsMapper.add(goods);
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
	public NewReturn update(Goods goods) {
		NewReturn re = new NewReturn();
		if(goods.getId() == null){
			re.setCode("-1");
			re.setMessage("未给出主键 id");
			return re; 
		}
		int temp = goodsMapper.update(goods);
		if(temp > 0){
			re.setCode("1");
			re.setMessage("商品信息更新成功!");
		}else{
			re.setCode("0");
			re.setMessage("商品信息更新失败!");
		}
		return re;
	}

	@Override
	public NewReturn delete(Goods goods) {
		NewReturn re = new NewReturn();
		if(goods.getId() == null){
			re.setCode("-1");
			re.setMessage("未给出主键 id");
			return re ;
		}
		int temp = goodsMapper.delete(goods);
		if(temp > 0){
			re.setCode("1");
			re.setMessage("商品下架成功!");
		}else{
			re.setCode("0");
			re.setMessage("商品下架失败!");
		}
		return re;
	}

	@Override
	public NewReturn deleteByMenuId(Integer typeid) {
		NewReturn re = new NewReturn();
		if(typeid == null){
			re.setCode("-1");
			re.setMessage("未给出主键 typeid");
			return re ;
		}
		int temp = goodsMapper.deleteByMenuId(typeid);
		if(temp > 0){
			re.setCode("1");
			re.setMessage("商品下架成功!");
		}else{
			re.setCode("0");
			re.setMessage("商品下架失败!");
		}
		return re;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Goods findById(Integer id,Integer managerid) {
		Goods goods = new Goods();
		goods.setId(id);
		goods.setManagerId(managerid);
		NewReturn re = find(goods);
		if(!(("1").equals(re.getCode())))
			return null;
		else
			return ((List<Goods>)re.getObj()).get(0);
	}

}
