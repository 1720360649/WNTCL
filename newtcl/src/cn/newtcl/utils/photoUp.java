package cn.newtcl.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import cn.newtcl.service.impl.ImUserService;
import sun.misc.BASE64Decoder;

public class photoUp { 
	public boolean out(String str,String number) {
		
		Logger log = Logger.getLogger(ImUserService.class);
		String path = getClass().getResource("/").toString().replace("/newtcl/WEB-INF/classes/", "").replace("file:", "");
		log.info(path);
		
		//str BASE64解码
		try {

			BASE64Decoder decoder=new BASE64Decoder();
			byte[] bytes;
			bytes = decoder.decodeBuffer(str);
			File file=new File(path+"/Avatars/"+number+".jpg");
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
