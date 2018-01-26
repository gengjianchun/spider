package com.geng.ZiRoomSpider.Support;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geng.ZiRoomSpider.Dao.RoomInfoDao;
import com.geng.ZiRoomSpider.bean.DetailInfo;
import com.geng.ZiRoomSpider.bean.Room;
public class InfoHandler implements Runnable{

	private  RoomInfoDao roomInfoDao;
	private BlockingQueue<Room> queue;
	
	public InfoHandler(RoomInfoDao roomInfoDao, BlockingQueue<Room> queue){
		this.roomInfoDao = roomInfoDao;
		this.queue = queue;
	}
	
	public  void handle(String url, Integer roomId) throws InterruptedException, IOException {
		DetailInfo detail = new DetailInfo();
		Connection connect = Jsoup.connect("http://"+url);
		connect.headers(HeaderLoader.getHeaderMap());
		Document doc = connect.get();
		
		Element detailUl = doc.getElementsByAttributeValue("class", "detail_room").first();
		//房屋面积
		 Elements roomDetail = detailUl.getElementsByTag("li");
		 Element areaDetailLi = roomDetail.first();
		 String areaDetail = areaDetailLi.text();
		 if(areaDetail != null && areaDetail.startsWith("面积")){
			 detail.setArea(getLastNum(areaDetail));
		 }
		//朝向
		 Element orLi = areaDetailLi.nextElementSibling();
		 String orien = orLi.text().trim();
		 if(orien != null && orien.startsWith("朝向")){
			 detail.setOrientation(orien.substring(orien.length()-1, orien.length()));
		 }
		 //户型
		 Element roomNumUl = orLi.nextElementSibling();
		 String numText = roomNumUl.text().replaceAll(" ", "");
		 if(numText != null && numText.startsWith("户型")){
			 detail.setRoomNum(numText.substring(numText.indexOf("户型")+3, numText.indexOf("户型")+4));
		 }
		 
		Elements womanElements = doc.getElementsByAttributeValue("class", "woman");
		if(womanElements != null){
			detail.setWomanNum(womanElements.size());
		}
		Elements womanLastElements = doc.getElementsByAttributeValue("class", "woman  last");
		if(womanLastElements != null){
			detail.setWomanNum(detail.getWomanNum()+womanLastElements.size());
		}
		Elements manElements = doc.getElementsByAttributeValue("class", "man");
		if(manElements != null){
			detail.setMenNum(manElements.size());
		}
		
		detail.setId(roomId);
		
		roomInfoDao.saveDetail(detail);
		
	}

	private  String getLastNum(String lo) {
		boolean start = false;
		String num = "";
		for(int i = lo.length() - 1; i >= 0; i--){
			if((lo.charAt(i) >= 48 && lo.charAt(i) <= 57) || lo.charAt(i) == 46){
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

	

	public void run() {
		while(true){
			Room room = null;
			try {
				room = queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String detailUrl = room.getUrl();
			try {
				handle(detailUrl,room.getId());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	

}

