package com.geng.ZiRoomSpider.Dao;

import org.apache.ibatis.annotations.Param;

public interface RoomInfoDao {

	/**
	 * 
	 * @param url
	 * @param subLine
	 * @param station
	 * @param distance
	 * @param title
	 * @param price
	 */
	void add(@Param("url")String url, @Param("subLine")String subLine, @Param("station")String station, 
			@Param("distance")String distance, @Param("title")String title, @Param("price")String price);

}
