package com.geng.ZiRoomSpider.Support;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;


public class HeaderLoader {

	private static Logger logger = Logger.getLogger(HeaderLoader.class);
	public static Properties load(){
		InputStream in = HeaderLoader.class.getClassLoader().getResourceAsStream("header.properties");
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			logger.info("load header.properties 出错");
			e.printStackTrace();
		}
		return p;
	}
	
	public static Map<String, String> getHeaderMap(){
		Properties p = load();
		Map<String, String> header = new HashMap<String, String>();
		Set<Object> keySet = p.keySet();
		for(Object obj : keySet){
			String key = (String) obj;
			String val = p.getProperty(key);
			header.put(key, val);
		}
		return header;
	}
}
