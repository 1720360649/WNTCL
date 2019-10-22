package cn.newtcl.controller;

public class Getstatic extends SubDishList{
	
	private static Getstatic sub;
	
	public Getstatic() {
		
	}
	
	public static Getstatic getSubDishList(){
		if(sub == null){
			sub = new Getstatic();
		}
		return sub;
	}
}
