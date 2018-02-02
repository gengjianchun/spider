package com.geng.ZiRoomSpider.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.geng.ZiRoomSpider.Dao.RoomInfoDao;

@Component
public class ShedualService  {

	@Autowired
	private RoomInfoDao roomInfoDao;
	
	@Scheduled(cron="* * 0/1  * * ? ")   //每小时执行一次
	public void show(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)-3);
		roomInfoDao.delOldRoom(calendar.getTime());
		
		roomInfoDao.delFullRoomDetail();
		
	}
	
}
