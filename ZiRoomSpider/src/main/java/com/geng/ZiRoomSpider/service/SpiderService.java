package com.geng.ZiRoomSpider.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.geng.ZiRoomSpider.Support.GetTargetDocument;
import com.geng.ZiRoomSpider.Support.HeaderLoader;
import com.geng.ZiRoomSpider.Support.HtmlParser;
import com.geng.ZiRoomSpider.Support.InfoHandler;




@Service
public class SpiderService {

	@Value("${targeturl}")
	private String targeturl;

	private Logger logger = Logger.getLogger(SpiderService.class);
	@Autowired
	private InfoHandler infoHandler;
	
	public void start() throws Exception {
		while(targeturl != null){
			Document doc = GetTargetDocument.get(targeturl);
			//解析html
			List<String> roomInfo = HtmlParser.parse(doc);
			//处理信息
			infoHandler.handle(roomInfo);
			
			System.out.println(roomInfo);
			String nextPage = HtmlParser.getNextPage(doc);
			if(targeturl.equals(nextPage)){
				System.out.println("----end----");
				System.out.println(nextPage);
				System.out.println("----end----");
				break;
			}else{
				targeturl = nextPage;
			}
			Thread.sleep(20000);
				
		}
		
		
	}
	
}
