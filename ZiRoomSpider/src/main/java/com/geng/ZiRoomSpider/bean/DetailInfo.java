package com.geng.ZiRoomSpider.bean;

public class DetailInfo {

	//房间id
	public Integer id;
	
	
	public String area;
	public String orientation;
	//卧室数
	public String roomNum;
	//女室友数量
	public int womanNum;
	
	//男室友数
	public int menNum;
	
	
	


	public int getWomanNum() {
		return womanNum;
	}

	public void setWomanNum(int womanNum) {
		this.womanNum = womanNum;
	}

	public int getMenNum() {
		return menNum;
	}

	public void setMenNum(int menNum) {
		this.menNum = menNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}


	
	
	
	
}
