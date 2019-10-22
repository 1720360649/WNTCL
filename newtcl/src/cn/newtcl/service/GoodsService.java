package cn.newtcl.service;/*
  User: 晨梦意志
  Date: 2019/6/3
  Time: 17:21
  Notes:
*/

import cn.newtcl.entity.Goods;
import cn.newtcl.entity.NewReturn;

public interface GoodsService {
    NewReturn find(Goods goods);

    Integer count(Goods goods);

    NewReturn findByName( Goods goods);

    NewReturn add(Goods goods);

    NewReturn update(Goods goods);

    NewReturn delete(Goods goods);

    NewReturn deleteByMenuId(Integer typeid);
    
    Goods findById(Integer id,Integer managerid);
}
