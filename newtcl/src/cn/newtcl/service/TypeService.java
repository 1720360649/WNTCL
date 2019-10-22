package cn.newtcl.service;

import java.util.List;

import cn.newtcl.entity.NewReturn;
import cn.newtcl.entity.Type;
import cn.newtcl.entity.TypeAndGoods;

public interface TypeService {
	/**
	 * @param id(可选) name(可选,模糊查询) manageid(必填)
	 * @return List<Goods>
	 */
    NewReturn find(Type type);
    
    /**
     * @param   id(必填) manageid(必填)
     * @return
     */
    Integer count (Type type);
    
    /**
     * @param 	name(必填) manageid(必填)
     * @return 
     */
    NewReturn add(Type type);
    
    /**
     * @param  id(必填) name(必填)
     *      * @return
     */
    NewReturn update(Type type);
    
    /**
     * @param goods id(必填)
     * @return
     */
    NewReturn delete(Type type);
    
    NewReturn findTypeAndGoods(Type type);
}
