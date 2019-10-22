package cn.newtcl.controller.Vcode;

import java.awt.Color;
import java.awt.Font;

public class Fonts {
	
	public static Font getfont(){
		Font f=null;
		int temp=(int) (Math.random()*9);
		switch (temp) {
		case 1:
			f=new Font("����",Font.PLAIN,27);
			break;
			
		case 2:
			f=new Font("Microsoft Yahei",Font.PLAIN,24);
			break;
			
		case 3:
			f=new Font("��������",Font.PLAIN,25);
			break;
			
		case 4:
			f=new Font("������κ",Font.PLAIN,25);
			break;
			
		case 5:
			f=new Font("����Ҧ��",Font.PLAIN,24);
			break;
			
		case 6:
			f=new Font("΢�����",Font.PLAIN,25);
			break;
			
		case 7:
			f=new Font("΢����Բ",Font.PLAIN,25);
			break;
			
		case 8:
			f=new Font("�����п�",Font.PLAIN,25);
			break;

		default:
			f=new Font("���Ĳ���",Font.PLAIN,24);
			break;
		}
		
		return f;
	}
	
	public static Color getcolor(){
		Color c=null;
		int temp=(int) (Math.random()*9);
		switch (temp) {
		case 1:
			c=new Color(255, 0, 0);
			break;
			
		case 2:
			c=new Color(102, 0, 255);
			break;
			
		case 3:
			c=new Color(0, 0, 255);
			break;
			
		case 4:
			c=new Color(184, 134, 11);
			break;
			
		case 5:
			c=new Color(102, 0, 0);
			break;
			
		case 6:
			c=new Color(0, 255, 0);
			break;
			
		case 7:
			c=new Color(51, 255, 255);
			break;
			
		case 8:
			c=new Color(122, 55, 139);
			break;

		default:
			c=new Color(0, 0, 0);
			break;
		}
		
		return c;
	}
	
}
