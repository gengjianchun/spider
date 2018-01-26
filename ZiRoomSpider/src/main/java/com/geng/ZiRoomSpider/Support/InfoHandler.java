package com.geng.ZiRoomSpider.Support;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geng.ZiRoomSpider.Dao.RoomInfoDao;
@Component
public class InfoHandler {

	@Autowired
	private  RoomInfoDao roomInfoDao;
	
	public  void handle(List<String> roomInfo) {
		for(String info : roomInfo){
			String[] infos = info.split("--");
			//url
			String url = infos[0];
			String subLine = getSubLine(infos[1]);
			String station = null;
			if(subLine != null){
				station = getStation(infos[1]);
			}
			String distance = getLastNum(infos[1]);
			String title = infos[2];
			String price = getLastNum(infos[3]);
			roomInfoDao.add(url,subLine,station,distance,title,price);
			 
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
	

}

