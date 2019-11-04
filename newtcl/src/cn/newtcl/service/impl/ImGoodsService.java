package cn.newtcl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.newtcl.dao.GoodsMapper;
import cn.newtcl.entity.Goods;
import cn.newtcl.entity.NewReturn;
import cn.newtcl.service.GoodsService;
import cn.newtcl.utils.shopPhotoUp;

@Service
public class ImGoodsService implements GoodsService {
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Resource(name="transactionManager")
	private DataSourceTransactionManager transactionManager;

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
		shopPhotoUp up = new shopPhotoUp();
		//事务
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		
		if(goods.getName() == "" ||goods.getName() == null || goods.getPhoto() == "" || goods.getPhoto() == null || goods.getNowprice() == null || goods.getTypeId() == null || goods.getManagerId() == null){
				re.setCode("-1");
				re.setMessage("有未填必填项!");
				return re ;
		}	
	
		try {
			String tempphoto = goods.getPhoto();
			goods.setPhoto("temp");
			int temp = goodsMapper.add(goods);
			Goods tempgood = new Goods();
			tempgood.setId(goods.getId());
			tempgood.setPhoto("/upload/image/"+goods.getId()+".jpg");
			re = update(tempgood);
			
			if(temp>0 && up.out(tempphoto,goods.getId()) && "1".equals(re.getCode())){	
				transactionManager.commit(status);
				re.setCode("1");
				re.setMessage("添加成功!");
			}else{
				transactionManager.rollback(status);
				re.setCode("0");
				re.setMessage("添加失败!请重试!");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
        	// 回滚
			transactionManager.rollback(status);
			re.setCode("0");
			re.setMessage("未知异常添加失败!请重试!");
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
		if(goods.getId() <= 44){
			re.setCode("-2");
			re.setMessage("受保护数据,无法删除!");
			return re ;
		}

		int temp = goodsMapper.delete(goods);
		if(temp > 0){
			re.setCode("1");
			re.setMessage("商品删除成功!");
		}else{
			re.setCode("0");
			re.setMessage("商品删除失败!");
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
