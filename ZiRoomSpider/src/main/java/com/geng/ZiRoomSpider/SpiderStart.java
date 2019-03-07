package com.geng.ZiRoomSpider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.geng.ZiRoomSpider.service.SpiderService;

public class SpiderStart {



	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//asss
		SpiderService service = context.getBean(SpiderService.class);
		service.start();

	}
}
