package com.geng.ZiRoomSpider.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.geng.ZiRoomSpider.Dao.RoomInfoDao;
import com.geng.ZiRoomSpider.Support.GetTargetDocument;
import com.geng.ZiRoomSpider.Support.HeaderLoader;
import com.geng.ZiRoomSpider.Support.HtmlParser;
import com.geng.ZiRoomSpider.Support.InfoHandler;
import com.geng.ZiRoomSpider.bean.Room;




@Service
public class SpiderService {

	@Value("${targeturl}")
	private String targeturl;

	private Logger logger = Logger.getLogger(SpiderService.class);
	@Autowired
	private RoomInfoDao roomInfoDao;
	
	private static BlockingQueue<Room> queue = new ArrayBlockingQueue<Room>(50);
	
	public void start() throws Exception {
			
			//解析粗略信息
			new Thread(new HtmlParser(targeturl, roomInfoDao, queue)).start();
			new Thread(new InfoHandler(roomInfoDao, queue)).start();
		
		
	}
	
}
