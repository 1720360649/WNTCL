package cn.newtcl.controller;

import java.util.HashMap;
import java.util.Map;

public class Getstatic extends SubDishList{
	
	private static Map<Integer, Getstatic> map = null;
	
	public Getstatic() {
		
	}
	
	public static Getstatic getSubDishList(Integer id){
		Getstatic sub = null;
		
		if(map == null || map.size() < 1){
			map = new HashMap<Integer, Getstatic>();
		}
		if(!map.containsKey(id)){
			sub = new Getstatic();
			map.put(id, sub);
			return sub;
		}else{
			return map.get(id);
		}
		
	}
	
	synchronized public static boolean setSubDishList(Integer id,Getstatic sub){
		if((id == null || id < 1) || (sub == null || !(sub instanceof Getstatic))){
			return false;
		}else{
			map.put(id, sub);
			return true;	
		}
	}
}
