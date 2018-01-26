package com.geng.ZiRoomSpider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlTest {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		Properties p = new Properties();
		InputStream in = MysqlTest.class.getClassLoader().getResourceAsStream("database.properties");
		p.load(in);
		
		String url = (String) p.get("url");
		String user = p.getProperty("user");
		String pwd = p.getProperty("pwd");
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = DriverManager.getConnection(url, user, pwd);
		System.out.println(url);
	}
}
