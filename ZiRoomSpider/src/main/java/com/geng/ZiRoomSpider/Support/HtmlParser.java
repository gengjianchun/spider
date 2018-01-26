package com.geng.ZiRoomSpider.Support;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

	public static List<String> parse(Document doc){
		List<String> room = new ArrayList<String>();
		
		
		//id 为houseList 的ul 包含多个li标签，每个li标签为一个房源
		Element ul = doc.getElementById("houseList");
		
		//li标签列表
		ListIterator<Element> liTags = ul.getElementsByTag("li").listIterator();
		//遍历解析各房源信息
		while(liTags.hasNext()){
			String info = "";
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
			info = url+"--"+detail+"--"+title+"--"+price;
			room.add(info);
		}
		
		
		return room;
	}

	public static String getNextPage(Document doc) {
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
}
