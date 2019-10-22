package cn.newtcl.controller.Vcode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class images
 */
@Controller
@RequestMapping("images")
public class images extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public images() {
        super();
        // TODO Auto-generated constructor stub
    }

	@RequestMapping("/do")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			//设置页面不缓存
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);
			
			//在内存中创建图象
			int imageWidth = 75;//图片的宽度
			int imageHeight = 35;//图片的高度
			    
			BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			
			//获取图形句柄
			Graphics graphics = image.getGraphics();
			
			//使背景透明
			image = ((Graphics2D) graphics).getDeviceConfiguration().createCompatibleImage(imageWidth, imageHeight,Transparency.TRANSLUCENT);
			graphics.dispose();
			graphics = image.createGraphics();
			
			//获取随机验证码
			String rcodes = Randomcode.getcode(5);
			
			//将验证码存储至会话
			request.getSession().setAttribute("rcodes", rcodes);
			
			//绘画形状
			//句柄颜色
//			graphics.setColor(Color.pink);
//			填充指定的矩形
//			graphics.fillRect(0, 0, imageWidth, imageHeight);
			
			//绘画字符串
			//句柄颜色
			graphics.setColor(Fonts.getcolor());
			//句柄字体
			graphics.setFont(Fonts.getfont());
			//使用此句柄的当前字体和颜色绘制由指定 string 给定的文本
			graphics.drawString(rcodes, 3, 27);
			    
			    
			//图像生效
			graphics.dispose();
			
			//关闭缓存,兼容IE５.５
			ImageIO.setUseCache(false);
			// 输出图象到页面,JDK1.4前需解码,1.4开始引入ImageIO进行图像输出
			ImageIO.write(image, "png",response.getOutputStream());
			response.flushBuffer();
			
		} catch (IOException e) {
		e.printStackTrace();
		}
	}

}
