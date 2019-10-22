package cn.newtcl.controller.Vcode;


public class Randomcode {

	public static String getcode(int n){
		String code = "";
		
		while(n>0){
			int temp=(int) (Math.random()*9);
			code+=temp;
			n--;
		}
		return code;
	}	
}