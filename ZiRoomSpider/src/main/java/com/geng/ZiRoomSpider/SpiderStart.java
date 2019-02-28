package com.geng.ZiRoomSpider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.geng.ZiRoomSpider.service.SpiderService;

public class SpiderStart {

//测试代码冲突
	//啊啊啊啊啊
	//wwwwwwwwwwwwwwwwwww
	//aaaaa
	///qqqqqq
	//zzzzzzz



	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		SpiderService service = context.getBean(SpiderService.class);
		service.start();
		
	}
}
