package com.geng.ZiRoomSpider.Dao;


import java.util.Date;

import com.geng.ZiRoomSpider.bean.DetailInfo;
import com.geng.ZiRoomSpider.bean.Room;

public interface RoomInfoDao {

	Integer add(Room room);

	void saveDetail(DetailInfo detail);

	Room getByUrl(String url);


	void updateRoom(Room room);

	DetailInfo getDetailById(Integer id);

	void updateDetail(DetailInfo detail);

	int delOldRoom(Date date);

}
