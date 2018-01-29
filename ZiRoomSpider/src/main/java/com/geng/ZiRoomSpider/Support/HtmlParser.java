package com.geng.ZiRoomSpider.Support;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.BlockingQueue;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.geng.ZiRoomSpider.Dao.RoomInfoDao;
import com.geng.ZiRoomSpider.bean.Room;

public class HtmlParser implements Runnable {
	
	private String targeturl;

	private RoomInfoDao roomInfoDao;
	private BlockingQueue<Room> insertQueue;
	private BlockingQueue<Room> updateQueue;
	
	public HtmlParser(String targeturl, RoomInfoDao roomInfoDao, BlockingQueue<Room> insertQueue, BlockingQueue<Room> updateQueue){
		this.targeturl = targeturl;
		this.roomInfoDao = roomInfoDao;
		this.insertQueue = insertQueue;
		this.updateQueue = updateQueue;
	}
	
	
	public  void parse(Document doc){
		//id 为houseList 的ul 包含多个li标签，每个li标签为一个房源
		Element ul = doc.getElementById("houseList");
		
		//li标签列表
		ListIterator<Element> liTags = ul.getElementsByTag("li").listIterator();
		//遍历解析各房源信息
		while(liTags.hasNext()){
			Room room = new Room();

			//各房源li标签，下含3个div   1-图片  2-描述 3-价格
			Element li = liTags.next();
			
			//房源描述信息div
			Element descrip = li.getElementsByTag("div").get(1);
			//房源名称 和链接
			Element aTag = descrip.getElementsByTag("a").first();
			String title = aTag.text();
			String url = aTag.attr("href").substring(2);
			//详情
			Element detailDiv = descrip.getElementsByAttributeValue("class", "detail").first();
			String detail = detailDiv.getElementsByTag("p").get(1).text();
			
			//价格div
			Element priceDiv = li.getElementsByAttributeValue("class", "priceDetail").first();
			String price = priceDiv.getElementsByTag("p").first().text();
			room.setTitle(title);
			room.setUrl(url);
			room.setDistance(this.getLastNum(detail));
			room.setSubLine(this.getSubLine(detail));
			room.setStation(this.getStation(detail));
			room.setPrice(this.getLastNum(price));
			Room oldRoom = roomInfoDao.getByUrl(room.getUrl());
			if(oldRoom != null && oldRoom.getId() != null){
				room.setId(oldRoom.getId());
				roomInfoDao.updateRoom(room);
				System.out.println("update room:"+room);
				try {
					updateQueue.put(room);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				Integer id = roomInfoDao.add(room);
				System.out.println("insert room:"+room);
				if(id != null){
					try {
						insertQueue.put(room);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
		}
		
	}

	
	
	private  String getLastNum(String lo) {
		boolean start = false;
		String num = "";
		for(int i = lo.length() - 1; i >= 0; i--){
			if(lo.charAt(i) >= 48 && lo.charAt(i) <= 57){
				num = lo.charAt(i)+num;
				start = true;
			}else if(start){
				break;
			}
		}
		if(num.length() == 0){
			return null;
		}
		return num;
	}

	private  String getStation(String lo) {
		int line = lo.indexOf("线");
		int state = lo.indexOf("站");
		if(state >= 0){
			return lo.substring(line+1,state+1);
		}
		return null;
	}

	private  String getSubLine(String lo) {
		//截取“距"
		if(lo.indexOf("距") >= 0){
			lo = lo.substring(1);
		}else{
			return null;
		}
		//“线”的位置索引
		int line = lo.indexOf("线");
		if(line >= 0){
			lo = lo.substring(0, line+1);
		}else{
			return null;
		}
		return lo;
	}
	
	
	
	public  String getNextPage(Document doc) {
		ListIterator<Element> next = doc.getElementsByAttributeValue("class", "next").listIterator();
		while(next.hasNext()){
			Element n = next.next();
			String url = n.attr("href");
			if(url != null){
				return "http:"+url;
			}
		}
		
		return null;
	}



	public void run() {
		String url = targeturl;
		while(url != null){
			Document doc;
			try {
				doc = GetTargetDocument.get(url);
				String nextPage = getNextPage(doc);
				if(url.equals(nextPage)){
					url = targeturl;
					continue;
				}else{
					url = nextPage;
				}
				
				parse(doc);
				Thread.sleep(20000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
