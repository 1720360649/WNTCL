package cn.newtcl.dao;/*
  User: 晨梦意志
  Date: 2019/6/3
  Time: 17:19
  Notes:
*/

import org.springframework.stereotype.Repository;

import cn.newtcl.entity.Goods;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsMapper {
	/**
	 * @param goods id(可选) name(可选,模糊查询) type(可选) status(可选)
	 * @return List<Goods>
	 */
    List<Goods> find(Goods goods);
    /**
     * @param goods  id(必填) type(必填)
     * @return
     */
    Integer count (Goods goods);
    
    /**
     * @param goods manageid(必填) type_id(必填) status(只不填)
     * @return 
     */
    Integer add(Goods goods);
    /**
     * @param goods id(不填),status(不填) 其余必填
     *      * @return
     */
    Integer update(Goods goods);
    /**
     * @param goods id(必填) 其余可选
     * @return
     */
    Integer delete(Goods goods);
    /**
     * @param typeid typeid(只填)
     * @return
     */
    Integer deleteByMenuId(Integer typeid);

}
