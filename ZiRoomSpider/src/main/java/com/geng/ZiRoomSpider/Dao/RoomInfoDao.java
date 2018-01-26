package com.geng.ZiRoomSpider.Dao;


import com.geng.ZiRoomSpider.bean.DetailInfo;
import com.geng.ZiRoomSpider.bean.Room;

public interface RoomInfoDao {

	Integer add(Room room);

	void saveDetail(DetailInfo detail);

}
