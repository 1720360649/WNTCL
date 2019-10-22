package cn.newtcl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.newtcl.entity.Type;
import cn.newtcl.entity.TypeAndGoods;

@Repository
public interface TypeMapper {
	
	/**
	 * @param id(可选) name(可选,模糊查询) manageid(必填)
	 * @return List<Goods>
	 */
    List<Type> find(Type type);
    
    /**
     * @param   id(必填) manageid(必填)
     * @return
     */
    Integer count (Type type);
    
    /**
     * @param 	name(必填) manageid(必填)
     * @return 
     */
    Integer add(Type type);
    
    /**
     * @param  id(必填) name(必填)
     *      * @return
     */
    Integer update(Type type);
    
    /**
     * @param goods id(必填)
     * @return
     */
    Integer delete(Type type);
    
    List<TypeAndGoods> findTypeAndGoods(Type type);
	
}