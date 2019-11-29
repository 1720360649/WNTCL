package cn.newtcl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import cn.newtcl.entity.subDish;

public class SubDishList {
	//未分配订单列表
	private static List<subDish> list = null;
	//已分配订单集合
	private static Map<String, ArrayList<subDish>> map = null;
	//已制作订单集合
	private static Map<Integer, List<subDish>> overlist = null;
	//员工上次操作时间
	private static Map<String, Date> lasttime = null;
	//在线员工数
	private static int num = 0;
	// 回收定时器
	private static Timer maintimer = null;
	//回收间隔时间
	private int looptime = 2;
	//超时时间
	private long outtime = 2;
	
	private static String URL = "https://openapi.alipay.com/gateway.do";
	private static String APP_ID = "2019090666987149";
	private static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCEp5m4HP+asATEqk28BW+4VpouLJYYvRcdB5s9aT2luOzZXlkaomkRDqvDz64s7nZf9K4y/XgNHriJ8agzGmnxg8/cZ+uM/RWd3qGlaJn3xfCVNWC/A5wF4NNdVscfe3Dch5AdCZvvlfhBulGjZ3hs6FL6DIa7Subqoq10r4d+xljRxNAfjyH7gJrwJMsClN2XJLHXS0tH59ZkAEtjn3Oxe+3qNbn/6jdBliQmwy11xgl3gqEVmGFN7I/5iGpPOgvnqhLATJ8uw/g33bpnJkJhV866qWkv3yF4P/pp/9kAUMrpoUUSYIqF72vHO31LiTszQCAkLdW9suYjYFQvPxJdAgMBAAECggEAGn7nYzDXlOwHZ8ikUiO71tPlY8egCzzpevDj/vYgTBONqzT/EyTRW4jUof2/SlYu6qeSi7i429YBAfx0nhBujMDJKbrP/48lXCz1mHY2Gpk8nnDU7BIqfboUPZZxm0YqmsGw9TvQG6CWv3oZiEvIb+hPeoY5+vrQ+JiZkK7mn1lwu/Ljqogs1gz5X25ZFj1t3FOVdaSptNRu5jpcLaRS8kqyVw2I6SgOc5/i/JE6uwYryBdBCOCx+av9RuYB3Yf69B9caIkgV5/f0oUy95JdkW/2nUpNG/YXefoYH87xuPB86yoPVi0+DphND7gI0GfHL2KSZV3d6HrRpekjl4V1+QKBgQDKz0jyJcY2LFQr7alF7pewLIubw5EZzpHX9ArShwKGAi1U40NP7emjwIMhN1yd9CCH8gtoC9hL17OgngrgGeNNaAeZp54yT+htc7OojGCVQmol+msmajTXV1Ywxr7WqYDimGM1f+sZZAl72h87nxupz94Uspj/ouH8N5ITUJptQwKBgQCnchU9bL5VaYU7uTpOlL3Zo60J2XwD1oqbd2etMi8p3nI31Yxx/zVtN8607u1SSdA92Ww/uVZrs1umRoARPQIXoxDNgY37lQupspUOW75W9Pfn7zN1JUZnYmXu1jzLROTWDtjdEk9/N3FifX9IRnJZCqNygoM1zzvjN+fh93W33wKBgQCRY8RBg37y+xhkXX4KGh84NtbmlW6B96pqDBNuFUdj1k+1GS76L+5ca/gVaeDFg2uGyagPsN4gkMO/E01slmJy7W71YkpwewR+4oEbBXd/yRrbmwgRg/XZokyxrujEbsNsrh4BDnD1xNdfqGDXlxp3xALLB7XTHWuwKg5YNmJejQKBgG/nKBay08iVCntNYJAfi3klj4DjnaOa/2rCs8DlputeRArYZlHQ8s2Y3EXC30t9FPQgl91GaWD0rq1CtFfQ49MUISiWCmM1ymOQe1jrKxtF/187VrJVROXWtrSU7HAUDLKZnYiwsfwc+2OM50FQAx9KoszmN/YAFs/VDtzO2hcVAoGBALryvL+p7D4zpPRiZXKorlXvzUv9Mh4RxzVgwUHhvm/AqBTzVQDiMX++nTmlePzlIYdYYvULGXdhW+cOp+Sv1tY1WIHGn/dmT7c1taqvfmeFpDVkwEjRCNa+cTWHSeuw4BIBYsw8aVEh+lgNW0S9tD7yp1obvAsxIlDusUqJOZV/";
	private static String FORMAT = "json";
	private static String CHARSET = "utf-8";
	private static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1WDhyUZZLEcRQDmBnTfg3INawb5GuWD2q+VgJwsvxgwDoV8qQKQgTqx/+vl6pDqNhbMSeD506Y8bXc5OAndEZkj8ijTSQUzAWQRN8BoSTElGhbkGCG98djsz8GaPQ9BLFd7uMYlbLSwKLe7JssFPqlU/iu6CGJJjOW3wl5sycxpNQ7rLgTq+AOk1IjwqWWI06o7ohZEnMFZbQsKmXJWohtntkxyE15wHLEM4PymnB7QO54YZDd9215saCZbmmx3VKaRR6fxrgOvzpUWXjuKJSktcJ/eVRlR1RMfZjv3KUkPmT76C2DhH4xlyveUket+76DmJv06uS8Lf9h6MemHHvQIDAQAB";
	private static String SIGN_TYPE = "RSA2";
	
	//初始化支付宝接口
	private AlipayClient  alipayClient = null;

	public AlipayClient getAlipayClient() {
		if(alipayClient == null)
			alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		return alipayClient;
	}

	public SubDishList() {
		destruction();
		list = new ArrayList<subDish>();
		map = new HashMap<String, ArrayList<subDish>>();
		setOverlist(new HashMap<Integer, List<subDish>>());
		lasttime = new HashMap<String, Date>();
	}

	public List<subDish> getList() {
		
		return list;
	}

	synchronized public void setList(List<subDish> li) {
		list = li;
	}

	public Map<String, ArrayList<subDish>> getMap() {
		return map;
	}

	synchronized public void setMap(Map<String, ArrayList<subDish>> mapi) {
		map = mapi;
	}

	public Map<String, Date> getLasttime() {
		return lasttime;
	}

	synchronized public void setLasttime(Map<String, Date> lasttimei) {
		lasttime = lasttimei;
	}

	public int getNum() {
		return num;
	}

	synchronized public void setNum(int numi) {
		num = numi;
	}
	
	public static Map<Integer, List<subDish>> getOverlist() {
		return overlist;
	}

	synchronized public static void setOverlist(Map<Integer, List<subDish>> overlist) {
		SubDishList.overlist = overlist;
	}

	//定时回收,避免厨师端意外退出!
	public void destruction() {
		
		maintimer = new Timer(true);
		maintimer.schedule(new TimerTask() {
			@Override
			public void run() {
				Date now = new Date();
				if(getLasttime().size() != 0 && getLasttime() != null){
					Iterator<String> itr = lasttime.keySet().iterator();
					while(itr.hasNext()){
						String temp = itr.next();
						Date last = lasttime.get(temp);
						long dmm = now.getTime() - last.getTime();
						long minutes = dmm/(1000 * 60);
						if(minutes >= outtime){
							List<subDish> li = getMap().get(temp);
							//将列表内菜品回收
							if(li != null && li.size() != 0)
								for(int i=li.size();i<0;i--){
									list.add(0,li.get(i));
								}
							lasttime.remove(temp);
							getMap().remove(temp);
							num--;
						}
					}
				}
			}
		}, 0, looptime*60*1000);
	}

	//订单分配分配器
	synchronized public List<subDish> distributor(String id){
			
		ArrayList<subDish> ulist = null;
		
		int lsize = 0;
		if(getList() != null && getList().size() != 0){
			lsize = getList().size();
		}
		int size = 0;
		if(getMap().get(id)!=null && getMap().get(id).size()!=0){
			size = getMap().get(id).size();
			ulist = getMap().get(id);
		}
		
		if(size >= 4){
			
		}else if(lsize > 0){
			ulist = getMap().get(id);
			size = 0;
			if(ulist!=null && size !=0){
				size = ulist.size();
			}

			if(lsize >= (num*4)){
				if(ulist == null && size == 0)
					ulist = new ArrayList<subDish>();
				for(int i = size;i<4;i++){
					ulist.add(getList().get(0));
					getList().remove(0);
				}
			}else if(num == 1){
				if(ulist == null && size == 0)
					ulist = new ArrayList<subDish>();
					int temp = lsize;
					for(int i = size;i<4;i++){
						if(temp == 0)
							break;
						if(getList().get(0) != null){
							ulist.add(getList().get(0));
							getList().remove(0);
							temp--;
						}
					}
			}else if(lsize == num){
				if(ulist == null && size == 0)
					ulist = new ArrayList<subDish>();
				ulist.add(getList().get(0));
				getList().remove(0);
			}else if(lsize >= (num*3)){
				if(ulist == null && size == 0)
					ulist = new ArrayList<subDish>();
				int temp = lsize;
				for(int i = size;i<3;i++){
					if(temp == 0)
						break;
					if(getList().get(0) != null){
						ulist.add(getList().get(0));
						getList().remove(0);
						temp--;
					}
				}
			}else if(lsize >= (num*2)){
				if(ulist == null && size == 0)
					ulist = new ArrayList<subDish>();
				int temp = lsize;
				for(int i = size;i<2;i++){
					if(temp == 0)
						break;
					if(getList().get(0) != null){
						ulist.add(getList().get(0));
						getList().remove(0);
						temp--;
					}
				}
			}else{
				if(ulist == null && size == 0)
					ulist = new ArrayList<subDish>();
					int temp = lsize;
					for(int i = size;i<4;i++){
						if(temp == 0)
							break;
						if(getList().get(0) != null){
							ulist.add(getList().get(0));
							getList().remove(0);
							temp --;
						}
					}
			}
			
			getMap().put(id, ulist);
		}
			
		return ulist;
	}

}
