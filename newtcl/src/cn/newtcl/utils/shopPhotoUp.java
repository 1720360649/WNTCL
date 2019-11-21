package cn.newtcl.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import cn.newtcl.service.impl.ImUserService;
import sun.misc.BASE64Decoder;

public class shopPhotoUp { 
	public boolean out(String str,Integer number) {
		
		Logger log = Logger.getLogger(ImUserService.class);
		String path = getClass().getResource("/").toString().replace("/WEB-INF/classes/", "").replace("file:", "");
		
		//str BASE64解码
		try {
			String temp = str.substring(str.indexOf(",")+1);//去掉base64字符串的开头部分
			str =temp;
			log.info(str);
			BASE64Decoder decoder=new BASE64Decoder();
			byte[] bytes;
			bytes = decoder.decodeBuffer(str);
			for (int i = 0; i < bytes.length; ++i) {
			       if (bytes[i] < 0) {
			    	   bytes[i] += 256;
			       }
			}
			
			File file=new File(path+"/upload/image/"+number+".jpg");	
			FileOutputStream fos=new FileOutputStream(file); 
			fos.write(bytes);
			fos.flush();
			fos.close();
		
		} catch (IOException e) {
			e.printStackTrace();
			log.info("错误");
			return false;
		} 

	return true;
	}
}
