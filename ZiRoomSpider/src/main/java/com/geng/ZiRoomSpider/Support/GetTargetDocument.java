package com.geng.ZiRoomSpider.Support;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetTargetDocument {

	public static Document get(String targetUrl) throws Exception{
		Connection connect = Jsoup.connect(targetUrl);
		connect.headers(HeaderLoader.getHeaderMap());
		return connect.get();
	}
}
